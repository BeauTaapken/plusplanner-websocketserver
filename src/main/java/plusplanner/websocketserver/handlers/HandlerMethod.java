package plusplanner.websocketserver.handlers;

import org.json.JSONObject;
import plusplanner.websocketserver.reactor.TypeReactor;

public abstract class HandlerMethod extends TypeReactor {

    public HandlerMethod(String type){
        super(type);
    }

    public abstract void handle(JSONObject jsonObject);
}
