package plusplanner.websocketserver.handlers;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class HandlerConfig {
//    @Bean
//    public Map<String, BaseController> baseControllerMap(){
//        final Map<String, BaseController> controllerMap = new HashMap<>();
//        return controllerMap;
//    }

    @Bean
    public List<HandlerMethod> handlerMethods(){
        final List<HandlerMethod> methods = new ArrayList<>();

        return methods;
    }
}
