package plusplanner.websocketserver.controllers;

import com.auth0.jwt.interfaces.DecodedJWT;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

public class MessageController extends BaseController {
    @Autowired
    private RestTemplate restTemplate;

    @Override
    void delete(JSONObject data, DecodedJWT token) {

    }

    @Override
    void update(JSONObject data, DecodedJWT token) {

    }

    @Override
    void put(JSONObject data, DecodedJWT token) {

    }
}
