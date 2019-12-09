package plusplanner.websocketserver.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JsonObj {
    private String task;
    private String time;
    private String UUID;
    private Object element;
    public JsonObj() {
    }
}
