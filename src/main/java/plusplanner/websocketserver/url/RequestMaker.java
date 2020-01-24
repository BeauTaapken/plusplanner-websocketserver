package plusplanner.websocketserver.url;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Component
public class RequestMaker {
    private final RestTemplate restTemplate;
    private final Map<String, Map<String, String>> urlMap;

    @Autowired
    public RequestMaker(RestTemplate restTemplate, Map<String, Map<String, String>> urlMap) {
        this.restTemplate = restTemplate;
        this.urlMap = urlMap;
    }

    public void doRequest(JSONObject jsonObject){
        final HttpEntity httpEntity = new HttpEntity(jsonObject.get("element").toString());
        final String url = urlMap.get(jsonObject.getString("type"))
                .get(jsonObject.getString("action"));
        restTemplate.postForObject(url, httpEntity, String.class);
    }
}
