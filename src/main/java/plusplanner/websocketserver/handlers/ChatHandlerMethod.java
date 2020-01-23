package plusplanner.websocketserver.handlers;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class ChatHandlerMethod extends HandlerMethod {
    private final RestTemplate restTemplate;

    @Autowired
    public ChatHandlerMethod(RestTemplate restTemplate){
        super("chat");
        this.restTemplate = restTemplate;
    }

    @Override
    void delete(JSONObject jsonObject) {

    }

    @Override
    void update(JSONObject jsonObject) {

    }

    @Override
    void create(JSONObject jsonObject) {
        final HttpEntity httpEntity = new HttpEntity(jsonObject.get("element").toString());
        restTemplate.postForObject("https://plus-planner-channel-service/chat/create", httpEntity, String.class);
    }
}
