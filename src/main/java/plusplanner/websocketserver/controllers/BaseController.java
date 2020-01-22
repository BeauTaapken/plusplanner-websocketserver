package plusplanner.websocketserver.controllers;

import com.auth0.jwt.interfaces.DecodedJWT;
import org.json.JSONObject;

@Deprecated
public abstract class BaseController {

    public void crudDistribution(JSONObject json, DecodedJWT token){
        switch (json.getString("action")) {
            case "delete":
                delete(json, token);
                break;
            case "update":
                update(json, token);
                break;
            case "create":
                put(json, token);
                break;
        }
    }

    abstract void delete(JSONObject data, DecodedJWT token);

    abstract void update(JSONObject data, DecodedJWT token);

    abstract void put(JSONObject data, DecodedJWT token);
}
