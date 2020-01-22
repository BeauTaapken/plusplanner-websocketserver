package plusplanner.websocketserver.controllers;

import com.auth0.jwt.interfaces.DecodedJWT;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Deprecated
public class MessageController extends BaseController {
    @Autowired
    private RestTemplate restTemplate;
    private DateTimeFormatter formatter;

    @Override
    void delete(JSONObject data, DecodedJWT token) {
        formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy hh-mm");
        restTemplate.postForObject
                ("http://plus-planner-message-service/subpart/update" +
                                (new JSONObject(data.get("element")).getString("messageid")),
                        new HttpEntity<>(new HttpHeaders()), String.class);

    }

    @Override
    void update(JSONObject data, DecodedJWT token) {
        restTemplate.postForObject
                ("http://plus-planner-message-service/subpart/update",
                        new HttpEntity<>(data.get("element").toString(), new HttpHeaders()), String.class);
    }

    @Override
    void put(JSONObject data, DecodedJWT token) {
        JSONObject message = new JSONObject(data.get("element"));
        restTemplate.postForObject
                ("http://plus-planner-message-service/subpart/update",
                        new HttpEntity<>("{\"messageid\":\"" + message.getString("messageid") +
                                "\",\"channelid\":\"" + message.getString("channelid") +
                                "\",\"userid\":\"" + token.getClaim("uid").asString() +
                                "\",\"content\":\"" + message.getString("content") +
                                "\",\"senddate\":\"" + LocalDateTime.now().format(formatter) + "\"}",
                                new HttpHeaders()), String.class);
    }
}
