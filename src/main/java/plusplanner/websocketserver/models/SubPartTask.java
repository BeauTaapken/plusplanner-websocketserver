package plusplanner.websocketserver.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SubPartTask {
    private String task;
    private String time;
    private String UUID;
    private SubPart element;

    public SubPartTask() {
    }
}
