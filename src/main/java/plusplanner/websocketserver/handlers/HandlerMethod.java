package plusplanner.websocketserver.handlers;

import org.json.JSONObject;
import plusplanner.websocketserver.reactor.TypeReactor;

public abstract class HandlerMethod extends TypeReactor {

    public HandlerMethod(String type){
        super(type);
    }

    public void handle(JSONObject jsonObject){
        switch (jsonObject.getString("action")) {
            case "delete":
                delete(jsonObject);
                break;
            case "update":
                update(jsonObject);
                break;
            case "create":
                create(jsonObject);
                break;
        }
    }

    abstract void delete(JSONObject jsonObject);
    abstract void update(JSONObject jsonObject);
    abstract void create(JSONObject jsonObject);
}
