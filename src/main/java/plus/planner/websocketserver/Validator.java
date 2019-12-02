package plus.planner.websocketserver;

import java.util.UUID;

public class Validator {

    public  Validator(){

    }

    public boolean testUUID(String uuid)
    {
        try{
            UUID Uuid = UUID.fromString(uuid);
            return true;
        } catch (IllegalArgumentException exception){
            System.out.println(exception);
            return false;
        }
    }
}
