package plusplanner.websocketserver;

import com.auth0.jwt.interfaces.DecodedJWT;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Component;
import plusplanner.websocketserver.controllers.BaseController;
import plusplanner.websocketserver.controllers.SubPartController;

@Component
public class PathDictionairy {
    private BaseController controller;

    public PathDictionairy() {
    }

    public void ControlPathing(String json, DecodedJWT token) {
        System.out.println(json);
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject(json);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        if(jsonObject != null)
        {
            switch (jsonObject.getString("type")) {
                case "message":
                    //controller = new MessageController();
                   // controller.crudDistribution(jsonObject, token);
                    break;
                case "task":
                    controller = new SubPartController();
                    controller.crudDistribution(jsonObject, token);
                    break;
                default:
                    break;
            }
        }
    }
}
