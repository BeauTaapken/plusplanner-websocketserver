package plusplanner.websocketserver.validators;

import org.json.JSONObject;
import org.springframework.stereotype.Component;
import plusplanner.websocketserver.models.Permission;
import plusplanner.websocketserver.models.RoleType;

@Component
public class PartValidator extends Validator {

    public PartValidator() {
        super("part");
    }

    @Override
    public boolean validate(JSONObject jsonObject, Permission permission, String uid) {
        return permission.getRole().ordinal() >= RoleType.ADMIN.ordinal();
    }
}
