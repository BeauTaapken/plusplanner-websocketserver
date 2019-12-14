package plusplanner.websocketserver.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.web.client.RestTemplate;
import plusplanner.websocketserver.models.MessageTask;

import java.net.MalformedURLException;

public class MessageController {
    @Autowired
    private ObjectMapper mapper;

    @Autowired
    private RestTemplate restTemplate;

    public MessageController() {
        mapper = new ObjectMapper();
    }

    public void saveMessage(String json) {
        try {
            MessageTask m = mapper.readValue(json, MessageTask.class);
            String s = mapper.writeValueAsString(m.getElement());
            restTemplate.postForObject("http://plus-planner-channel-service/message/create/" + s, new HttpEntity<>(new HttpHeaders()), String.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    public void deleteMessage(String json) {
        try {
            MessageTask m = mapper.readValue(json, MessageTask.class);
            restTemplate.postForObject("http://localhost:8084/message/delete/" + m.getElement().getMessageid(), new HttpEntity<String>(new HttpHeaders()), String.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}
