package accountService.executor;

/**
 * Created with IntelliJ IDEA.
 * User: vanik
 * Date: 11.12.13
 * Time: 22:39
 * To change this template use File | Settings | File Templates.
 */
import accountService.handlers.TResultHandler;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TExecutor {
    public <T> T execQuery(Connection connection,
                           String query,
                           TResultHandler<T> handler)
            throws SQLException {
        Statement stmt = connection.createStatement();
        stmt.execute(query);
        ResultSet result = stmt.getResultSet();
        T value = handler.handle(result);
        result.close();
        stmt.close();

        return value;
    }

}
