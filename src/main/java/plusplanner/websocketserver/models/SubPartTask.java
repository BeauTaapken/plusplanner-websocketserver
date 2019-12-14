package plusplanner.websocketserver.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SubPartTask {
    private Enum<crud> task;
    private String type;
    private SubPart element;

    public SubPartTask() {
    }
}
