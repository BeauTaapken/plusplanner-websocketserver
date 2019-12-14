package plusplanner.websocketserver.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JsonObj {
    private Enum<crud> task;
    private String type;
    private Object element;
    public JsonObj() {
    }
}
