package accountService.executor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PreparedExecutor {
    public void execUpdate(Connection connection,String name, String password) {
        try{
            connection.setAutoCommit(false);
            String update = "insert into users(userName, password) values(?,?)";
            PreparedStatement stmt = connection.prepareStatement(update);
            stmt.setString(1,name);
            stmt.setString(2,password);
            stmt.executeUpdate();
            connection.commit();
            stmt.close();
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        }
    }
}
