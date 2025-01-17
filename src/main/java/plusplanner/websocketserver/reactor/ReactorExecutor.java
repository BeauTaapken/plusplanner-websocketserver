package plusplanner.websocketserver.reactor;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import plusplanner.websocketserver.models.Permission;
import plusplanner.websocketserver.models.SessionWrapper;
import plusplanner.websocketserver.socket.BroadCaster;
import plusplanner.websocketserver.url.RequestMaker;
import plusplanner.websocketserver.validators.Validator;

import java.util.List;

@Component
public class ReactorExecutor {
    private final Logger logger = LoggerFactory.getLogger(ReactorExecutor.class);
    private final List<Validator> validators;
    private final RequestMaker requestMaker;
    private final BroadCaster broadCaster;

    @Autowired
    public ReactorExecutor(List<Validator> validators, RequestMaker requestMaker, BroadCaster broadCaster) {
        this.validators = validators;
        this.requestMaker = requestMaker;
        this.broadCaster = broadCaster;
    }

    private <T extends TypeReactor> T getReactor(List<T> reactors, String type) {
        return reactors.stream()
                .filter(x -> x.getHandlingType().equals(type))
                .findFirst().orElseThrow();
    }

    public boolean handle(String message, SessionWrapper sessionWrapper) {
        try {
            logger.info("parsing json");
            final JSONObject jsonObject = new JSONObject(message);
            final String type = jsonObject.getString("type");
            logger.info("getting reactors for: {}", type);
            final Validator validator = getReactor(validators, type);
            logger.info("validating");
            final String projectid =  jsonObject.getString("projectid");
            final Permission permission = sessionWrapper.getPermissions().stream()
                    .filter(x -> x.getProjectid().equals(projectid))
                    .findFirst().orElseThrow();
            if (!validator.validate(jsonObject, permission, sessionWrapper.getUid())) {
                logger.error("message is invalid");
                return false;
            }
            logger.info("using handler");
            requestMaker.doRequest(jsonObject);
            logger.info("broadcasting");
            broadCaster.broadCast(jsonObject, projectid);
            return true;
        } catch (Exception e) {
            logger.error("an unexpected error happened: {}", e.getMessage());
            return false;
        }
    }
}
