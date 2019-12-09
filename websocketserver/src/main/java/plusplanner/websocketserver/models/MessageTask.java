package plusplanner.websocketserver.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MessageTask {

    private String task;
    private String time;
    private String UUID;
    private Message element;
    public MessageTask() {
    }
}
