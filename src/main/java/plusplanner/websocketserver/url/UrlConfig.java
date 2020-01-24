package plusplanner.websocketserver.url;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class UrlConfig {
    @Bean
    public Map<String, Map<String, String>> urls() {
        final Map<String, Map<String, String>> urls = new HashMap<>();

        final Map<String, String> channelMap = new HashMap<>();
        channelMap.put("create", "https://plus-planner-channel-service/channel/create");

        urls.put("channel", channelMap);

        final Map<String, String> chatMap = new HashMap<>();
        chatMap.put("create", "https://plus-planner-channel-service/chat/create");

        urls.put("chat", chatMap);

        final Map<String, String> messageMap = new HashMap<>();
        messageMap.put("create", "https://plus-planner-message-service/message/create");

        urls.put("message", messageMap);

        final Map<String, String> partMap = new HashMap<>();
        partMap.put("create", "https://plus-planner-container-service/part/create");

        urls.put("part", partMap);

        final Map<String, String> roleMap = new HashMap<>();
        roleMap.put("create", "https://plus-planner-role-management-service/role/create");
        roleMap.put("update","https://plus-planner-role-management-service/role/update");
        roleMap.put("delete","https://plus-planner-role-management-service/role/create");

        urls.put("role",roleMap);

        final Map<String, String> subPartMap = new HashMap<>();
        subPartMap.put("create","https://plus-planner-subpart-service/subpart/create");
        subPartMap.put("update", "https://plus-planner-subpart-service/subpart/update");

        urls.put("subpart", subPartMap);

        return urls;
    }
}
