package plusplanner.websocketserver.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.web.client.RestTemplate;
import plusplanner.websocketserver.models.SubPartTask;

public class SubPartController extends baseController{
    @Autowired
    private ObjectMapper mapper;

    @Autowired
    private RestTemplate restTemplate;

    public SubPartController() {
        mapper = new ObjectMapper();
    }
    public void saveElement(String json){
        restTemplate.postForObject("http://localhost:8081/subpart/create/" + json, new HttpEntity<>(new HttpHeaders()), String.class);
    }

    public void deleteElement(String json){
        try {
            SubPartTask p = mapper.readValue(json, SubPartTask.class);
            restTemplate.postForObject("http://localhost:8081/subpart/delete/" + p.getElement().getPartid(), new HttpEntity<>(new HttpHeaders()), String.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    public void updateElement(String json){
        restTemplate.postForObject("http://localhost:8081/subpart/update/" + json, new HttpEntity<>(new HttpHeaders()), String.class);
    }
}
