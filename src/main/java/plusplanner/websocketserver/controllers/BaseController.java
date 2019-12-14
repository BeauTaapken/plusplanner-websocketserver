package plusplanner.websocketserver.controllers;

import org.json.JSONObject;

public abstract class BaseController {

    JSONObject json;

    public BaseController(JSONObject json) {
        this.json = json;
    }

    public void crudDistribution(){
        switch (json.getString("action")) {
            case "save":
                save(json);
                break;
            case "delete":
                delete(json);
                break;
            case "update":
                update(json);
                break;
            case "create":
                put(json);
                break;
        }
    }

    abstract void save(JSONObject data);

    abstract void delete(JSONObject data);

    abstract void update(JSONObject data);

    abstract void put(JSONObject data);
}
