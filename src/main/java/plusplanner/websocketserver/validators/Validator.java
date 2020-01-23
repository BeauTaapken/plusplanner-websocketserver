package plusplanner.websocketserver.validators;

import org.json.JSONObject;
import plusplanner.websocketserver.models.Permission;
import plusplanner.websocketserver.reactor.TypeReactor;

public abstract class Validator extends TypeReactor {

    public Validator(String type){super(type);}

    public abstract boolean validate(JSONObject jsonObject, Permission permission);
}
