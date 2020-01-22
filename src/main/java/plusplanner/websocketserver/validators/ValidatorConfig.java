package plusplanner.websocketserver.validators;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class ValidatorConfig {
    @Bean
    public List<Validator> validators(){
        final List<Validator> validators =  new ArrayList<>();

        return validators;
    }
}
