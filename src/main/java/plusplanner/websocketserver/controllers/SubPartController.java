package plusplanner.websocketserver.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.web.client.RestTemplate;
import plusplanner.websocketserver.models.MessageTask;
import plusplanner.websocketserver.models.SubPartTask;

import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class SubPartController {
    @Autowired
    private ObjectMapper mapper;

    @Autowired
    private RestTemplate restTemplate;

    public SubPartController() {
        mapper = new ObjectMapper();
    }

    URL url = null;
    URLConnection conn = null;
    public void saveSubPart(String json){
        try {
            SubPartTask p = mapper.readValue(json, SubPartTask.class);
            String s = mapper.writeValueAsString(p.getElement());
            restTemplate.postForObject("http://localhost:8081/subpart/create/" + s, new HttpEntity<>(new HttpHeaders()), String.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    public void deleteSubPart(String json){
        try {
            SubPartTask p = mapper.readValue(json, SubPartTask.class);
            restTemplate.postForObject("http://localhost:8081/subpart/delete/" + p.getElement().getPartid(), new HttpEntity<>(new HttpHeaders()), String.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    public void updateSubPart(String json){
        try {
            SubPartTask p = mapper.readValue(json, SubPartTask.class);
            String s = mapper.writeValueAsString(p.getElement());
            restTemplate.postForObject("http://localhost:8081/subpart/update/" + s, new HttpEntity<>(new HttpHeaders()), String.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}
