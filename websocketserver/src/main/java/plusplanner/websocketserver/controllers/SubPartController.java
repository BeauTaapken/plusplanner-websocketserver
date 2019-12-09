package plusplanner.websocketserver.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class SubPartController {
    @Autowired
    private ObjectMapper mapper;

    //TODO funish crud functionalities

    public SubPartController() {
        mapper = new ObjectMapper();
    }

    URL url = null;
    URLConnection conn = null;
    public void saveSubPart(String json){
        try {
            url = new URL("http://localhost:8081/subpart/create/" + json);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    public void deleteSubPart(String json){

        String id = "";
        try {
            url = new URL("http://localhost:8081/subpart/delete/" + id);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    public void updateSubPart(String json){

        String id = "";
        try {
            url = new URL("http://localhost:8081/subpart/update/" + json);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
}
