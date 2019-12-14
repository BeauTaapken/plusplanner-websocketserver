package plusplanner.websocketserver.models;

import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.socket.WebSocketSession;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class SessionWrapper {
    private WebSocketSession session;
    private String interest;
    private DecodedJWT userData;
}
