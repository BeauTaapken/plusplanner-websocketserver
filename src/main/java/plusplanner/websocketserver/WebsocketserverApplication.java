package plusplanner.websocketserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class WebsocketserverApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebsocketserverApplication.class, args);
	}

}
