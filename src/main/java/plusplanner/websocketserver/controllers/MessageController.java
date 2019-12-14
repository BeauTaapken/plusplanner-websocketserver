package plusplanner.websocketserver.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.web.client.RestTemplate;
import plusplanner.websocketserver.models.MessageTask;
import plusplanner.websocketserver.models.Message;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class MessageController extends baseController{
    @Autowired
    private ObjectMapper mapper;
    @Autowired
    private RestTemplate restTemplate;

    public MessageController() {
        mapper = new ObjectMapper();
    }

    @Override
    public void crudDistribution(String json) throws JsonProcessingException {
        super.crudDistribution(json);
    }

    public void saveElement(String json){
        restTemplate.postForObject("http://plus-planner-message-service/message/create/" + json, new HttpEntity<>(new HttpHeaders()), String.class);
    }

    public void deleteElement(String json){
        try {
            MessageTask m = mapper.readValue(json, MessageTask.class);
            restTemplate.postForObject("http://plus-planner-message-service/message/delete/" + m.getElement().getMessageid(), new HttpEntity<String>(new HttpHeaders()), String.class);
        } catch ( JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}
