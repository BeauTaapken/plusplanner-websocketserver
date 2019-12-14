package plusplanner.websocketserver.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import plusplanner.websocketserver.models.JsonObj;
import plusplanner.websocketserver.models.MessageTask;

public class BaseController {

    @Autowired
    private ObjectMapper mapper;

    public BaseController(){
        mapper = new ObjectMapper();
    }

    public void crudDistribution(String json) throws JsonProcessingException {
        JsonObj obj = mapper.readValue(json, JsonObj.class);
        switch(obj.getTask().toString()){
            case "save": saveElement(json); break;
            case "delete": deleteElement(json); break;
            case "update": updateElement(json); break;
            case "create": putElement(json); break;
        }
    }

    public void saveElement(String json){}
    public void deleteElement(String json){}
    public void updateElement(String json){}
    public void putElement(String json){}
}
