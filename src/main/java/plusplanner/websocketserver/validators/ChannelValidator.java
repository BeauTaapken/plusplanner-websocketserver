package plusplanner.websocketserver.validators;

import org.json.JSONObject;
import org.springframework.stereotype.Component;
import plusplanner.websocketserver.models.Permission;
import plusplanner.websocketserver.models.RoleType;

@Component
public class ChannelValidator extends Validator {

    public ChannelValidator(){
        super("channel");
    }

    @Override
    public boolean validate(JSONObject jsonObject, Permission permission, String uid) {
        return permission.getRole().ordinal() >= RoleType.ADMIN.ordinal();
    }
}
