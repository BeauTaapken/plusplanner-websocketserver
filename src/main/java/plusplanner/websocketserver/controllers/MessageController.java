package plusplanner.websocketserver.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import plusplanner.websocketserver.models.MessageTask;
import plusplanner.websocketserver.models.Message;

import java.io.File;
import java.io.IOException;
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
            String s = mapper.writeValueAsString(m.getElement());
            url = new URL("http://localhost:8084/message/create/" + s);
        } catch (MalformedURLException | JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    public void deleteMessage(String json){
        try {
            MessageTask m = mapper.readValue(json, MessageTask.class);
            url = new URL("http://localhost:8084/message/delete/" + m.getElement().getMessageid().toString());
        } catch (MalformedURLException | JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}
