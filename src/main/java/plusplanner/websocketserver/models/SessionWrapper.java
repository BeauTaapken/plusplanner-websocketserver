package plusplanner.websocketserver.models;

import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.socket.WebSocketSession;

@Getter
public class SessionWrapper {
    private final WebSocketSession session;
    @Setter
    private String interest;
    @Setter
    private DecodedJWT token;

    public SessionWrapper(WebSocketSession webSocketSession){
        this.session = webSocketSession;
    }

    public boolean isComplete(){
        return !(token == null || interest == null || session == null);
    }
}
