package plusplanner.websocketserver.handlers;

import com.auth0.jwt.interfaces.JWTVerifier;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import plusplanner.websocketserver.models.Role;
import plusplanner.websocketserver.models.SessionWrapper;

import java.util.Arrays;

public class TokenHandler {
    private final JWTVerifier jwtVerifier;
    private final ObjectMapper mapper;

    public TokenHandler(JWTVerifier jwtVerifier, ObjectMapper mapper) {
        this.jwtVerifier = jwtVerifier;
        this.mapper = mapper;
    }

    public boolean setToken(String message, SessionWrapper sessionWrapper) {
        try {
            final DocumentContext doc = JsonPath.parse(message);
            sessionWrapper.setToken(jwtVerifier.verify(doc.read("$.token", String.class)));
            final Role[] perms = mapper.readValue(sessionWrapper.getToken().getClaims().get("pms").asString(), Role[].class);
            if (Arrays.stream(perms).noneMatch(x -> x.getProjectid().equals(doc.read("$.interest", String.class)))) {
                return false;
            }
            sessionWrapper.setInterest(doc.read("$.interest", String.class));
        } catch (JsonProcessingException e) {
            return false;
        }
        return true;
    }
}
