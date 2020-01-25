package plusplanner.websocketserver;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import plusplanner.websocketserver.models.Permission;
import plusplanner.websocketserver.models.RoleType;

@TestPropertySource(locations="classpath:application-test.properties")
@SpringBootTest
public class websocketserverApplicationTests {
    public Permission role = new Permission("1", RoleType.GUEST);

    @Test
    public void getProjectId(){ Assert.assertEquals("1", role.getProjectid()); }

    @Test
    public void setProjectId(){
        role.setProjectid("2");
        Assert.assertEquals("2", role.getProjectid());
    }

    @Test
    public void getRole(){ Assert.assertEquals(RoleType.GUEST, role.getRole()); }

    @Test
    public void setRole(){
        role.setRole(RoleType.ADMIN);
        Assert.assertEquals(RoleType.ADMIN, role.getRole());
    }
}