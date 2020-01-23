package plusplanner.websocketserver.validators;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;
import org.springframework.stereotype.Component;
import plusplanner.websocketserver.models.Permission;
import plusplanner.websocketserver.models.RoleType;

@Component
public class PartValidator extends Validator {
    private final ObjectMapper objectMapper;

    public PartValidator(ObjectMapper objectMapper) {
        super("part");
        this.objectMapper = objectMapper;
    }

    @Override
    public boolean validate(JSONObject jsonObject, Permission permission) {
        return permission.getRole().ordinal() >= RoleType.MEMBER.ordinal();
    }
}
