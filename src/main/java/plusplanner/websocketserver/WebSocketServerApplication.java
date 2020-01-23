package plusplanner.websocketserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableEurekaClient
@SpringBootApplication
public class WebSocketServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebSocketServerApplication.class, args);
	}

}
