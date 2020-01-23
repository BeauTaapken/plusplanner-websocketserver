package plusplanner.websocketserver.validators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class ValidatorConfig {
    @Bean
    @Autowired
    public List<Validator> validators(PartValidator partValidator,
                                      MessageValidator messageValidator,
                                      SubPartValidator subPartValidator,
                                      ChannelValidator channelValidator,
                                      ChatValidator chatValidator){
        final List<Validator> validators =  new ArrayList<>();
        validators.add(partValidator);
        validators.add(messageValidator);
        validators.add(subPartValidator);
        validators.add(channelValidator);
        validators.add(chatValidator);
        return validators;
    }
}
