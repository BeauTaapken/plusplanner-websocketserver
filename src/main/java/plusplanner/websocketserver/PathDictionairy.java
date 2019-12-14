package plusplanner.websocketserver;

import org.json.JSONException;
import org.json.JSONObject;
import plusplanner.websocketserver.controllers.BaseController;
import plusplanner.websocketserver.controllers.MessageController;
import plusplanner.websocketserver.controllers.SubPartController;

public class PathDictionairy {

    private BaseController controller;

    public PathDictionairy() {
    }

    public void ControlPathing(String json) {
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
                    controller = new MessageController(jsonObject);
                    controller.crudDistribution();
                    break;
                case "task":
                    controller = new SubPartController(jsonObject);
                    controller.crudDistribution();
                    break;
                default:
                    break;
            }
        }
    }
}
