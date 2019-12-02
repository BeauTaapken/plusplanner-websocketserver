package plus.planner.websocketserver;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;

@ServerEndpoint("/endpoint")
public class WebsocketHandeler {
    private Session session;
    private static Set<WebsocketHandeler>
    private Validator validate;

    public WebsocketHandeler() {
        System.out.println("class loaded");
        validate = new Validator();
    }

    @OnOpen
    public void onOpen(Session session) {
        System.out.println("connection opened::" + session.getId());
        //TODO get all the chat information



        try {
            session.getBasicRemote().sendText("User joined");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @OnClose
    public void onClose(Session session) {
        System.out.println("connection closed::" + session.getId());
    }

    @OnMessage
    public void OnMessage(String message, Session session) {
        System.out.println("We got this message::" + message);
        try {
            session.getBasicRemote().sendText(message);
            JsonParser parser = new JsonParser();
            JsonElement element = parser.parse(message);
            JsonObject object = element.getAsJsonObject();
            String uuid = "";
            //Test if item contains a valid uuid.
            if(validate.testUUID(uuid) == true){
                //See if its Message or Planning in json
                switch (object.get("task").toString()){
                    case "message":
                        System.out.println("got message");

                        break;
                    case "update":
                        System.out.println("got a update");
                        break;
                    case "create":
                        System.out.println("got a create");
                        break;
                    case "delete":
                        System.out.println("got a delete");
                        break;
                    default:
                        System.out.println("Something went wrong");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @OnError
    public void onError(Throwable t) {
        t.printStackTrace();
    }

    private static void (){
        
    }
}