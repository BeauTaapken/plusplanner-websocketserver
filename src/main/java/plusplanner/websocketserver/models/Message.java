package plusplanner.websocketserver.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Message {
    private String messageid;
    private String channelid;
    private String content;
    @JsonIgnore
    private String userid;
    @JsonIgnore
    private String senddate;
    public Message() {
    }
}
