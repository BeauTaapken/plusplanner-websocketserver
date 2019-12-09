package plusplanner.websocketserver.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import plusplanner.websocketserver.models.MessageTask;
import plusplanner.websocketserver.models.Message;

import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class MessageController {
    @Autowired
    private ObjectMapper mapper;

    public MessageController() {
        mapper = new ObjectMapper();
    }

    URL url = null;
    URLConnection conn = null;
    public void saveMessage(String json){
        try {
            MessageTask m = mapper.readValue(json, MessageTask.class);
            url = new URL("http://localhost:8084/message/create/" + m.getElement());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    public void deleteMessage(String json){
        try {
            MessageTask m = mapper.readValue(json, MessageTask.class);
            url = new URL("http://localhost:8084/message/delete/" + m.getElement());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}
