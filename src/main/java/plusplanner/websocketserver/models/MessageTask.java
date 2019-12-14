package plusplanner.websocketserver.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MessageTask {
    private Enum<crud> task;
    private String type;
    private Message element;
    public MessageTask() {
    }
}
