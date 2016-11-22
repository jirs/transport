package ru.jirs.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import ru.jirs.service.TransportPositionService;
import ru.jirs.websocket.WSPositionListEncoder;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

    private static List<WebSocketSession> sessions = new CopyOnWriteArrayList<>();

    @Autowired
    private TransportPositionService transportPositionService;

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry webSocketHandlerRegistry) {
        webSocketHandlerRegistry.addHandler(new TransportPositionWSHandler(), "/gettransportpositions");
    }


    private class TransportPositionWSHandler extends TextWebSocketHandler {

        @Override
        public void afterConnectionEstablished(WebSocketSession session) throws Exception {
            sessions.add(session);
            session.sendMessage(new TextMessage(new WSPositionListEncoder().encode(transportPositionService.getForArea())));
        }

        @Override
        public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
            sessions.remove(session);
        }


    }

    public static List<WebSocketSession> getSessions() {
        return sessions;
    }
}
