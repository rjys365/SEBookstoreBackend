package cn.rjys365.sebookstorebackend.controller;

import cn.rjys365.sebookstorebackend.service.OrderService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.websocket.OnClose;
import jakarta.websocket.OnError;
import jakarta.websocket.OnOpen;
import jakarta.websocket.Session;
import jakarta.websocket.server.PathParam;
import jakarta.websocket.server.ServerEndpoint;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@ServerEndpoint("/websocket/order/{userId}/{uuid}")
@Component
public class WebsocketServer {
    private static final ConcurrentHashMap<Long, Session> SESSIONS
            = new ConcurrentHashMap<>();

    private final ObjectMapper mapper;
    private final OrderService orderService;

    public WebsocketServer(ObjectMapper mapper, OrderService orderService) {
        this.mapper = mapper;
        this.orderService = orderService;
    }

    @OnOpen
    public void onOpen(Session session, @PathParam("userId") Long userId, @PathParam("uuid") UUID uuid) throws IOException {

    }

    @OnClose
    public void onClose(Session session) throws IOException {
        // WebSocket connection closes
    }

    @OnError
    public void onError(Session session, Throwable throwable) {
        // Do error handling here
    }
}
