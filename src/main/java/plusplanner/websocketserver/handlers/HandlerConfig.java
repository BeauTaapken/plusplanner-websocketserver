package plusplanner.websocketserver.handlers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class HandlerConfig {

    @Bean
    @Autowired
    public List<HandlerMethod> handlerMethods(MessageHandlerMethod messageHandlerMethod,
                                              PartHandlerMethod partHandlerMethod,
                                              SubPartHandlerMethod subPartHandlerMethod){
        final List<HandlerMethod> methods = new ArrayList<>();
        methods.add(messageHandlerMethod);
        methods.add(partHandlerMethod);
        methods.add(subPartHandlerMethod);
        return methods;
    }
}
