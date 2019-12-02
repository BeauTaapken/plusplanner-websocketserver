package plus.planner.websocketserver;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.HashMap;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

@ServerEndpoint("/endpoint/{user}")
public class Endpoint {
    private Session session;
    private static Set<Endpoint> Endpoints
        = new CopyOnWriteArraySet<>();
    private static HashMap<String, String> users = new HashMap<>();
    private Validator validate;

    public Endpoint() {
        System.out.println("class loaded");
        validate = new Validator();
    }

    @OnOpen
    public void onOpen(Session session, @PathParam("user") String user) {
        System.out.println("connection opened::" + session.getId());
        this.session = session;
        Endpoints.add(this);
        users.put(session.getId(), user);

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
            if(validate.testUUID(uuid) == true && object.get("task").toString() == "AddSubPart"){
                //See if its Message or Planning in json
            } else {
                switch (object.get("task").toString()){
                    case "ChatMessage":
                        System.out.println("got message");
                        break;
                    case "UpdateSubPart":
                        System.out.println("got a update");
                        break;
                    case "DeleteSubPart":
                        System.out.println("got a delete");
                        break;
                    default:
                        System.out.println("Something went wrong");
                }
            }
            broadcast(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @OnError
    public void onError(Throwable t) {
        t.printStackTrace();
    }

    private static void broadcast(String message) throws IOException{
        Endpoints.forEach(endpoint -> {
            synchronized (endpoint) {
                try{
                    endpoint.session.getBasicRemote().sendText(message);
                } catch(IOException e) {
                    e.printStackTrace();;
                }
            }
        });
    }
}