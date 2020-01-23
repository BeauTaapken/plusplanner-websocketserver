package plusplanner.websocketserver.validators;

import org.json.JSONObject;
import org.springframework.stereotype.Component;
import plusplanner.websocketserver.models.Permission;

@Component
public class MessageValidator extends Validator {

    public MessageValidator(){
        super("message");
    }

    @Override
    public boolean validate(JSONObject jsonObject, Permission permission, String uid) {
        return jsonObject.getJSONObject("element").getString("userid").equals(uid);
    }
}
