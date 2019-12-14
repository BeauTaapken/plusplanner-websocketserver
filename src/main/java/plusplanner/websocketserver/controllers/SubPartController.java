package plusplanner.websocketserver.controllers;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.web.client.RestTemplate;

public class SubPartController extends BaseController {
    @Autowired
    private RestTemplate restTemplate;

    public SubPartController(JSONObject json) {
        super(json);
    }

    @Override
    void save(JSONObject data) {
        restTemplate.postForObject("http://localhost:8081/subpart/create/" + data.toString(), new HttpEntity<>(new HttpHeaders()), String.class);
    }

    @Override
    void delete(JSONObject data) {
        restTemplate.postForObject("http://localhost:8081/subpart/delete/" + data.getString("subpartid"), new HttpEntity<>(new HttpHeaders()), String.class);
    }

    @Override
    void update(JSONObject data) {

    }

    @Override
    void put(JSONObject data) {
        // restTemplate.postForObject("http://localhost:8081/subpart/update/" + data.toString(), new HttpEntity<>(new HttpHeaders()), String.class);
    }
}
