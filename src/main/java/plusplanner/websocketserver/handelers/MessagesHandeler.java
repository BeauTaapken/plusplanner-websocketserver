package plusplanner.websocketserver.handelers;

import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import plusplanner.websocketserver.PathDictionairy;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class MessagesHandeler extends TextWebSocketHandler {
    PathDictionairy pd = new PathDictionairy();
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
                pd.ControlPathing(message.toString());
                s.sendMessage(message);
            } catch (IOException e){
                e.printStackTrace();
            }
        }
    }
}
