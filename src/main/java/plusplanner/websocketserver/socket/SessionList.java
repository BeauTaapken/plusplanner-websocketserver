package plusplanner.websocketserver.socket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketSession;
import plusplanner.websocketserver.models.SessionWrapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Component
public class SessionList {
    private final Logger logger = LoggerFactory.getLogger(SessionList.class);
    private final List<SessionWrapper> sessions;

    public SessionList() {
        sessions = new CopyOnWriteArrayList<>();
    }

    public List<SessionWrapper> getSessions() {
        return new ArrayList<>(sessions);
    }

    public SessionWrapper getSession(WebSocketSession session) {
        return sessions.stream().filter(x -> x.getSession() == session).findAny().orElseThrow(IndexOutOfBoundsException::new);
    }

    public void addSession(WebSocketSession session) {
        sessions.add(new SessionWrapper(session));
    }

    public void removeSession(WebSocketSession session) {
        try {
            session.close();
        } catch (IOException e) {
            logger.debug("session already closed: {}", e.getMessage());
        }
        logger.info("closing socket: {}",session.getId());
        sessions.removeIf(x -> x.getSession() == session);
    }
}
