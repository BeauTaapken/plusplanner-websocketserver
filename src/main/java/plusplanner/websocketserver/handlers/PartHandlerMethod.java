package plusplanner.websocketserver.handlers;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class PartHandlerMethod extends HandlerMethod {
    private final RestTemplate restTemplate;

    @Autowired
    public PartHandlerMethod(RestTemplate restTemplate) {
        super("part");
        this.restTemplate = restTemplate;
    }

    @Override
    public void handle(JSONObject jsonObject) {
        final HttpEntity httpEntity = new HttpEntity(jsonObject.get("element").toString());
        restTemplate.postForObject("https://plus-planner-container-service/part/create", httpEntity, String.class);
    }
}
