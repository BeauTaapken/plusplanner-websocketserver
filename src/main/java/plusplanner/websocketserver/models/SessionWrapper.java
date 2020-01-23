package plusplanner.websocketserver.models;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.socket.WebSocketSession;

import java.util.List;

@Getter
public class SessionWrapper {
    private final WebSocketSession session;
    @Setter
    private List<Permission> permissions;

    @Setter
    private String uid;

    public SessionWrapper(WebSocketSession webSocketSession) {
        this.session = webSocketSession;
    }

    public boolean isComplete() {
        return !(uid == null || permissions == null || session == null);
    }
}
