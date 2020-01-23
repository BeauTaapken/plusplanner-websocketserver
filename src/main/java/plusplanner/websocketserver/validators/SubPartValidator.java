package plusplanner.websocketserver.validators;

import org.json.JSONObject;
import org.springframework.stereotype.Component;
import plusplanner.websocketserver.models.Permission;
import plusplanner.websocketserver.models.RoleType;

@Component
public class SubPartValidator extends Validator {

    public SubPartValidator(){
        super("subpart");
    }

    @Override
    public boolean validate(JSONObject jsonObject, Permission permission, String uid) {
        return permission.getRole().ordinal() >= RoleType.MEMBER.ordinal();
    }
}
