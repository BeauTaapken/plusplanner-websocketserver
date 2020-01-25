package plusplanner.websocketserver;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import plusplanner.websocketserver.models.Role;

@TestPropertySource(locations="classpath:application-test.properties")
@SpringBootTest
public class websocketserverApplicationTests {
    public Role role = new Role("1", "1");

    @Test
    public void getProjectId(){ Assert.assertEquals("1", role.getProjectid()); }

    @Test
    public void setProjectId(){
        role.setProjectid("2");
        Assert.assertEquals("2", role.getProjectid());
    }

    @Test
    public void getRole(){ Assert.assertEquals("1", role.getRole()); }

    @Test
    public void setRole(){
        role.setRole("2");
        Assert.assertEquals("2", role.getRole());
    }
}