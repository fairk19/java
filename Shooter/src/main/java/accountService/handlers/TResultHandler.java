package accountService.handlers;

/**
 * Created with IntelliJ IDEA.
 * User: vanik
 * Date: 11.12.13
 * Time: 22:43
 * To change this template use File | Settings | File Templates.
 */
import java.sql.ResultSet;
import java.sql.SQLException;

public interface TResultHandler<T> {
    T handle(ResultSet resultSet) throws SQLException;
}



