package plusplanner.websocketserver.handelers;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import plusplanner.websocketserver.PathDictionairy;
import plusplanner.websocketserver.models.Role;
import plusplanner.websocketserver.models.SessionWrapper;

import java.io.IOException;
import java.security.interfaces.RSAPublicKey;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;

import static plusplanner.websocketserver.utils.PemUtils.readPublicKeyFromFile;

@Component
public class MessagesHandler extends TextWebSocketHandler {
    private PathDictionairy pd = new PathDictionairy();
    private ObjectMapper objectMapper = new ObjectMapper();

    private List<SessionWrapper> sessions = new CopyOnWriteArrayList<>();


    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        sessions.add(new SessionWrapper(session, "", null));
    }

    private void closeConnection(WebSocketSession session) throws Exception {
        session.close();
        afterConnectionClosed(session, CloseStatus.NOT_ACCEPTABLE);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        super.afterConnectionClosed(session, status);
    }

    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        System.out.println(message.getPayload());
        SessionWrapper sessionWrapper = sessions.stream().filter(x -> x.getSession() == session).collect(Collectors.toList()).get(0);
        if (sessionWrapper.getInterest() == "") {
            sessionWrapper.setInterest(message.getPayload().split("\n")[0]);
            try {
                Algorithm algorithm = Algorithm.RSA512((RSAPublicKey) readPublicKeyFromFile("../plusplanner-websocketserver/src/main/resources/PublicKey.pem", "RSA"), null);
                JWTVerifier verifier = JWT.require(algorithm)
                        .withIssuer("plus-planner-token-service")
                        .build();
                sessionWrapper.setUserData(verifier.verify(message.getPayload().split("\n")[1]));
                Role[] perms = objectMapper.readValue(sessionWrapper.getUserData().getClaims().get("pms").asString(), Role[].class);
                if (!Arrays.stream(perms).filter(x -> x.getProjectid().equals(sessionWrapper.getInterest())).findFirst().isPresent()) {
                    closeConnection(session);
                }
            } catch (JWTVerificationException | IOException exception) {
                exception.printStackTrace();
                closeConnection(session);
            }
        } else {
            // Update all clients with message
            for (SessionWrapper sw : sessions) {
                try {
                    String interest = sessionWrapper.getInterest();
                    if (sw.getInterest().equals(interest)) {
                        WebSocketSession s = sw.getSession();
                        s.sendMessage(message);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            // Process the message and eventually call the Rest API
            pd.ControlPathing(message.getPayload());
        }
    }
}
