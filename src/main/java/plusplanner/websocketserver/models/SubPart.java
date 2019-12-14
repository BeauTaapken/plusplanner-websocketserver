package plusplanner.websocketserver.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

@Getter
@Setter
public class SubPart {
    private String UUID;
    private String description;
    private String state;
    private String enddate;

    @JsonIgnore
    private DateTimeFormat senddate;
    @JsonIgnore
    private String subpartid;
    @JsonIgnore
    private String subpartname;
    @JsonIgnore
    private String partid;

    public SubPart() {
    }
}
