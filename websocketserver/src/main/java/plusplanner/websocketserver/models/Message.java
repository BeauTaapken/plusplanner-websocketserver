package plusplanner.websocketserver.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Message {

    private String messageid;
    private String sender;
    private String content;
    private String chat;
    private String date;
    public Message() {
    }
}
