package plusplanner.websocketserver.validators;

import org.json.JSONObject;
import plusplanner.websocketserver.reactor.TypeReactor;
import plusplanner.websocketserver.models.SessionWrapper;

public abstract class Validator extends TypeReactor {

    public Validator(String type){super(type);}

    public abstract boolean validate(JSONObject jsonObject, SessionWrapper sessionWrapper);
}
