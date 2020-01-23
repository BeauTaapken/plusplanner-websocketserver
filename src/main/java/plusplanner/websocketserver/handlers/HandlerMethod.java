package plusplanner.websocketserver.handlers;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import plusplanner.websocketserver.reactor.TypeReactor;

public abstract class HandlerMethod extends TypeReactor {
    private final Logger logger = LoggerFactory.getLogger(HandlerMethod.class);

    public HandlerMethod(String type) {
        super(type);
    }

    public void handle(JSONObject jsonObject) {
        try {
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
        } catch (Exception e) {
            logger.error("error during handling object: {}", e.getMessage());
        }
    }

    abstract void delete(JSONObject jsonObject);

    abstract void update(JSONObject jsonObject);

    abstract void create(JSONObject jsonObject);
}
