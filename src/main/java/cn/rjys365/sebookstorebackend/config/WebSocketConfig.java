package cn.rjys365.sebookstorebackend.config;

import cn.rjys365.sebookstorebackend.controller.WebsocketServer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import org.springframework.web.socket.server.HandshakeInterceptor;

import java.util.Map;

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {
    private final WebsocketServer websocketServer;

    public WebSocketConfig(WebsocketServer websocketServer) {
        this.websocketServer = websocketServer;
    }

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(websocketServer, "/websocket/order/{userId}/{uuid}").setAllowedOrigins("*").addInterceptors(orderWebSocketInterceptor());
    }

    @Bean
    public HandshakeInterceptor orderWebSocketInterceptor() {
        return new HandshakeInterceptor() {
            public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response,
                                           WebSocketHandler wsHandler, Map<String, Object> attributes) throws Exception {

                // TODO
                String path = request.getURI().getPath();
                String[] parts = path.split("/");
                if (parts.length < 3) {
                    throw new IllegalArgumentException("Invalid WebSocket URL");
                }
                String userId = parts[parts.length - 2];
                String uuid = parts[parts.length - 1];

                attributes.put("userId", userId);
                attributes.put("uuid", uuid);
                return true;
            }

            public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response,
                                       WebSocketHandler wsHandler, Exception exception) {
                // Nothing to do after handshake
            }
        };
    }
}