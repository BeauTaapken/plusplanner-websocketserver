package plusplanner.websocketserver.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Message {

    private String messageid;
    private String channelid;
    private String userid;
    private String content;
    private String senddate;
    public Message() {
    }
}
