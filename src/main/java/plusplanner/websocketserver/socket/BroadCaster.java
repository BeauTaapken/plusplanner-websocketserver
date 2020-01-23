package plusplanner.websocketserver.socket;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import plusplanner.websocketserver.models.SessionWrapper;

import java.io.IOException;
import java.util.List;

@Component
public class BroadCaster {
    private final Logger logger = LoggerFactory.getLogger(BroadCaster.class);
    private final SessionList sessions;

    public BroadCaster(SessionList sessions) {
        this.sessions = sessions;
    }

    public void broadCast(JSONObject jsonObject, String projectid){
        final List<SessionWrapper> sws = sessions.getSessions();
        final TextMessage textMessage = new TextMessage(jsonObject.toString());
        for (SessionWrapper webSocketSession : sws) {
            if (webSocketSession.getInterests().contains(projectid)) {
                try {
                    webSocketSession.getSession().sendMessage(textMessage);
                } catch (IOException e) {
                    logger.debug("session closed: {}", e.getMessage());
                    sessions.removeSession(webSocketSession.getSession());
                }
            }
        }
    }
}
