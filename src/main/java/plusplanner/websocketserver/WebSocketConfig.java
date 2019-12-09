package plusplanner.websocketserver;

import lombok.ToString;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {
    PathDictionairy pd = new PathDictionairy();

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry webSocketHandlerRegistry) {
        webSocketHandlerRegistry.addHandler(new MessagesHandler(), "/messages").setAllowedOrigins("*");
    }

    class MessagesHandler extends TextWebSocketHandler {
        private List<WebSocketSession> sessions = new CopyOnWriteArrayList<>();

        public void afterConnectionEstablished(WebSocketSession session) throws Exception{
            sessions.add(session);
        }

        @Override
        public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
            super.afterConnectionClosed(session, status);
        }

        public void handleTextMessage(WebSocketSession session, TextMessage message){
            for(WebSocketSession s : sessions){
                try{
                    // pd.ControlPathing(message.toString());
                    s.sendMessage(message);
                } catch (IOException e){
                    e.printStackTrace();
                }
            }
        }
    }
}
