package accountService.makeConnection;

/**
 * Created with IntelliJ IDEA.
 * User: vanik
 * Date: 12.12.13
 * Time: 1:14
 * To change this template use File | Settings | File Templates.
 */
import java.sql.Connection;
import java.sql.SQLException;

public interface getConnection {
    public Connection getConnection() throws SQLException;
}
