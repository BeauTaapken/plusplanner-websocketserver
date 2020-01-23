package plusplanner.websocketserver.handlers;

import com.auth0.jwt.interfaces.JWTVerifier;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import org.springframework.stereotype.Component;
import plusplanner.websocketserver.models.Permission;
import plusplanner.websocketserver.models.SessionWrapper;

import java.util.Arrays;
import java.util.stream.Collectors;

@Component
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
            final Permission[] perms = mapper.readValue(sessionWrapper.getToken().getClaims().get("pms").asString(), Permission[].class);
            sessionWrapper.setInterests(Arrays.stream(perms).map(Permission::getProjectid).collect(Collectors.toList()));
        } catch (JsonProcessingException e) {
            return false;
        }
        return true;
    }
}
