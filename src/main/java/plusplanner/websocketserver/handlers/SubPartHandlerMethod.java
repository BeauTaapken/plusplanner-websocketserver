package plusplanner.websocketserver.handlers;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class SubPartHandlerMethod extends HandlerMethod {
    private final RestTemplate restTemplate;

    @Autowired
    public SubPartHandlerMethod(RestTemplate restTemplate) {
        super("subpart");
        this.restTemplate = restTemplate;
    }

    @Override
    void delete(JSONObject jsonObject) {

    }

    @Override
    void update(JSONObject jsonObject) {
        final HttpEntity httpEntity = new HttpEntity(jsonObject.get("element").toString());
        restTemplate.postForObject("http://plus-planner-subpart-service/subpart/update", httpEntity, String.class);
    }

    @Override
    void create(JSONObject jsonObject) {
        final HttpEntity httpEntity = new HttpEntity(jsonObject.get("element").toString());
        restTemplate.postForObject("http://plus-planner-subpart-service/subpart/create", httpEntity, String.class);
    }
}
