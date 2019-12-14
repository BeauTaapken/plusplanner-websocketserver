package plusplanner.websocketserver.handelers;

import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import plusplanner.websocketserver.PathDictionairy;
import plusplanner.websocketserver.models.SessionWrapper;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;

public class MessagesHandeler extends TextWebSocketHandler {
    PathDictionairy pd = new PathDictionairy();

    private List<SessionWrapper> sessions = new CopyOnWriteArrayList<>();

    public void afterConnectionEstablished(WebSocketSession session) throws Exception{
        sessions.add(new SessionWrapper(session, "", null));
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        super.afterConnectionClosed(session, status);
    }

    public void handleTextMessage(WebSocketSession session, TextMessage message){
        SessionWrapper sessionWrapper = sessions.stream().filter(x -> x.getSession() == session).collect(Collectors.toList()).get(0);
        for(SessionWrapper sw : sessions){
            try{
                String interest = sessionWrapper.getInterest();
                if(sw.getInterest().equals(interest)){
                    pd.ControlPathing(message.toString());
                    WebSocketSession s = sw.getSession();
                    s.sendMessage(message);
                }
            } catch (IOException e){
                e.printStackTrace();
            }
        }
    }
}
