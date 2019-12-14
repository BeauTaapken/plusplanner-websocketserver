package plusplanner.websocketserver.controllers;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

public class MessageController extends BaseController {
    @Autowired
    private RestTemplate restTemplate;

    public MessageController(JSONObject json) {
        super(json);
    }

    @Override
    void save(JSONObject data) {

    }

    @Override
    void delete(JSONObject data) {

    }

    @Override
    void update(JSONObject data) {

    }

    @Override
    void put(JSONObject data) {

    }
}
