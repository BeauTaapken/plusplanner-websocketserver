package plusplanner.websocketserver.socket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import plusplanner.websocketserver.reactor.ReactorExecutor;
import plusplanner.websocketserver.handlers.TokenHandler;
import plusplanner.websocketserver.models.SessionWrapper;

@Component
public class MessagesHandler extends TextWebSocketHandler {
    private final Logger logger = LoggerFactory.getLogger(MessagesHandler.class);
    private final SessionList sessionList;
    private final TokenHandler tokenHandler;
    private final ReactorExecutor reactorExecutor;

    @Autowired
    public MessagesHandler(SessionList sessionList, TokenHandler tokenHandler, ReactorExecutor reactorExecutor) {
        this.sessionList = sessionList;
        this.tokenHandler = tokenHandler;
        this.reactorExecutor = reactorExecutor;
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) {
        sessionList.addSession(session);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus){
        sessionList.removeSession(session);
    }

    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        logger.info("received message: {}", message.getPayload());
        final SessionWrapper sessionWrapper = sessionList.getSession(session);
        if (!sessionWrapper.isComplete()) {
            if (!tokenHandler.setToken(message.getPayload(), sessionWrapper)) {
                sessionList.removeSession(session);
            }
        } else if (!reactorExecutor.handle(message.getPayload(), sessionWrapper)) {
            sessionList.removeSession(session);
        }
    }
}
