package cn.rjys365.sebookstorebackend.controller;

import cn.rjys365.sebookstorebackend.dto.OrderDetailsDTO;
import cn.rjys365.sebookstorebackend.entities.Order;
import cn.rjys365.sebookstorebackend.service.OrderService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class WebsocketServer extends TextWebSocketHandler {
    private static final ConcurrentHashMap<Long, WebSocketSession> SESSIONS
            = new ConcurrentHashMap<>();

    private final ObjectMapper mapper;
    private final OrderService orderService;

    public WebsocketServer(ObjectMapper mapper, OrderService orderService) {
        this.mapper = mapper;
        this.orderService = orderService;
    }

    @Override
    @Transactional
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        String userIdString = (String) session.getAttributes().get("userId");
        Long userId = Long.valueOf(userIdString);
        String uuidString = (String) session.getAttributes().get("uuid");
        UUID uuid = UUID.fromString(uuidString);

        System.out.println("user " + userId + "connecting with order uuid " + uuid);

        if (SESSIONS.get(userId) != null) {
            session.close();
        }
        if (uuid != null) {
            Optional<Order> orderOptional = orderService.getOrderByUuid(uuid);
            if (orderOptional.isPresent()) {
                Order order=orderOptional.get();
                if(!order.getUserId().equals( userId))return;
                try {
                    session.sendMessage(new TextMessage(mapper.writeValueAsString(new OrderDetailsDTO(order))));
//                    session.close();
                } catch (Throwable throwable) {
                    System.out.println("WebSocket Error");
                    throwable.printStackTrace();
                }
            }
        }
        SESSIONS.put(userId, session);
        System.out.println("User " + userId + " start to listen orders");
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        String userIdString = (String) session.getAttributes().get("userId");
        Long userId = Long.valueOf(userIdString);
        SESSIONS.remove(userId);
    }

    @KafkaListener(topics = "order_response", groupId = "bookstore_backend")
    public void listenOrderResponse(OrderDetailsDTO orderDetailsDTO) {
        Long userId = orderDetailsDTO.getUserId();
        WebSocketSession session = SESSIONS.get(userId);
        if (session == null) return;
        try {
            session.sendMessage(new TextMessage(mapper.writeValueAsString(orderDetailsDTO)));
        } catch (Throwable throwable) {
            System.out.println("WebSocket Error");
            throwable.printStackTrace();
        }
    }
}