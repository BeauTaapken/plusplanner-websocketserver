package plusplanner.websocketserver;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import plusplanner.websocketserver.controllers.MessageController;
import plusplanner.websocketserver.controllers.SubPartController;
import plusplanner.websocketserver.models.JsonObj;

public class PathDictionairy {

    private MessageController mc;
    private SubPartController sc;
    private ObjectMapper mapper;
    public PathDictionairy() {
        mc = new MessageController();
        sc = new SubPartController();
        mapper = new ObjectMapper();
    }

    public void ControlPathing(String json){
        try {
            JsonObj obj = mapper.readValue(json, JsonObj.class);
            switch (obj.getType()){
                case "Message" :  mc.crudDistribution(json); break;
                case "Supbart":  mc.deleteMessage(json); break;
                default: break;
            }
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}
