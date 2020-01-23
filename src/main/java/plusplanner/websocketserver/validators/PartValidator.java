package plusplanner.websocketserver.validators;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;
import org.springframework.stereotype.Component;
import plusplanner.websocketserver.models.Permission;
import plusplanner.websocketserver.models.RoleType;
import plusplanner.websocketserver.models.SessionWrapper;

import java.util.Arrays;

@Component
public class PartValidator extends Validator {
    private final ObjectMapper objectMapper;

    public PartValidator(ObjectMapper objectMapper) {
        super("part");
        this.objectMapper = objectMapper;
    }

    @Override
    public boolean validate(JSONObject jsonObject, SessionWrapper sessionWrapper) {
        try {
            final Permission[] permissions = objectMapper.readValue(
                    sessionWrapper.getToken().getClaims().get("pms").asString(), Permission[].class);
            final Permission permission = Arrays.stream(permissions)
                    .filter(x -> x.getProjectid() == sessionWrapper.getInterest())
                    .findFirst().orElse(new Permission());
            return permission.getRole().ordinal() >= RoleType.MEMBER.ordinal();
        } catch (JsonProcessingException e) {
            return false;
        }
    }
}
