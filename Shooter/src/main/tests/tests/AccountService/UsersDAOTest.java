package tests.AccountService;

import accountService.MakeConnection;
import accountService.dao.UsersDAO;
import org.junit.Before;
import org.junit.Test;
import java.sql.Connection;
import static com.thoughtworks.selenium.SeleneseTestBase.assertEquals;

/**
 * Created by alexandr on 21.12.13.
 */
public class UsersDAOTest {

    private UsersDAO usersDAO;
    private static final String name = "User22";
    private static final String password = "User21";

    @Before
    public void setUp() throws Exception {
        Connection connection = MakeConnection.getConnection();
        usersDAO = new UsersDAO(connection);
        //usersDAO.setNewUser(name,password);
    }

    @Test
    public void testGetUserId() throws Exception {
        assertEquals("Users", usersDAO.getTableName());
        assertEquals(name, usersDAO.getByName(name).getName());
    }
}
