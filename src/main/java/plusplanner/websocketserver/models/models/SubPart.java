package plusplanner.websocketserver.models.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SubPart {
    private String subpartid;
    private String subpartname;
    private String description;
    private String state;
    private String enddate;
    private String partid;

    public SubPart() {
    }
}
