package plusplanner.websocketserver.controllers;

import com.auth0.jwt.interfaces.DecodedJWT;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class SubPartController extends BaseController {
    @Autowired
    private RestTemplate restTemplate;

    @Override
    void delete(JSONObject data, DecodedJWT token) {
        restTemplate.postForObject
                ("http://plus-planner-subpart-service/subpart/update" +
                                (new JSONObject(data.get("element")).getString("subpartid")),
                        new HttpEntity<>(new HttpHeaders()), String.class);
    }

    @Override
    void update(JSONObject data, DecodedJWT token) {
        restTemplate.postForObject
                ("http://plus-planner-subpart-service/subpart/update",
                        new HttpEntity<>(data.get("element").toString(), new HttpHeaders()), String.class);
    }

    @Override
    void put(JSONObject data, DecodedJWT token) {
        restTemplate.postForObject
                ("http://plus-planner-subpart-service/subpart/create",
                        new HttpEntity<>(data.get("element").toString(), new HttpHeaders()), String.class);

    }
}
