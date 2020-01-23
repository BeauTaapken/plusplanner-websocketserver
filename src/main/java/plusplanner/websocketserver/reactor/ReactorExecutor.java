package plusplanner.websocketserver.reactor;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import plusplanner.websocketserver.handlers.HandlerMethod;
import plusplanner.websocketserver.models.Permission;
import plusplanner.websocketserver.models.SessionWrapper;
import plusplanner.websocketserver.socket.BroadCaster;
import plusplanner.websocketserver.validators.Validator;

import java.util.List;

@Component
public class ReactorExecutor {
    private final List<HandlerMethod> handlerMethods;
    private final List<Validator> validators;
    private final BroadCaster broadCaster;

    @Autowired
    public ReactorExecutor(List<HandlerMethod> handlerMethods, List<Validator> validators, BroadCaster broadCaster) {
        this.handlerMethods = handlerMethods;
        this.validators = validators;
        this.broadCaster = broadCaster;
    }

    private <T extends TypeReactor> T getReactor(List<T> reactors, String type) {
        return reactors.stream()
                .filter(x -> x.getHandlingType() == type)
                .findFirst().orElseThrow();
    }

    public boolean handle(String message, SessionWrapper sessionWrapper) {
        try {
            final JSONObject jsonObject = new JSONObject(message);
            final String type = jsonObject.getString("type");
            final Validator validator = getReactor(validators, type);
            final HandlerMethod handlerMethod = getReactor(handlerMethods, type);
            final String projectid =  jsonObject.getString("projectid");
            final Permission permission = sessionWrapper.getPermissions().stream()
                    .filter(x -> x.getProjectid().equals(projectid))
                    .findFirst().orElseThrow();
            if (!validator.validate(jsonObject, permission)) {
                return false;
            }
            handlerMethod.handle(jsonObject);
            broadCaster.broadCast(jsonObject, projectid);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
