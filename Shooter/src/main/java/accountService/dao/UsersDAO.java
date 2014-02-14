package accountService.dao;

/**
 * Created with IntelliJ IDEA.
 * User: vanik
 * Date: 11.12.13
 * Time: 22:26
 * To change this template use File | Settings | File Templates.
 */
import accountService.dataSets.UsersDataSet;
import accountService.executor.PreparedExecutor;
import accountService.executor.TExecutor;
import accountService.handlers.TResultHandler;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsersDAO {
    private Connection con;

    public UsersDAO(Connection con) {
        this.con = con;
    }

    public String getTableName() throws SQLException{
        TExecutor exec = new TExecutor();
        return exec.execQuery(con, "show create table users", new TResultHandler<String>() {
            public String handle(ResultSet result) throws SQLException {

                if(!result.next()) {
                    return "There are not table users";
                } else {
                    return result.getString(1).toString();
                }

            }
        });
    }

    public UsersDataSet getById(long id) throws SQLException {
        TExecutor exec = new TExecutor();
        return exec.execQuery(con,"select * from users where id="+id, new TResultHandler<UsersDataSet>() {

            public UsersDataSet handle(ResultSet result) throws SQLException {
                result.next();
                return new UsersDataSet(result.getLong(1),result.getString(2),result.getString(3), false);
            }
        });
    }
    public UsersDataSet getByName(String name) throws SQLException {
        TExecutor exec = new TExecutor();
        return exec.execQuery(con,"select * from users where userName='"+name+"'", new TResultHandler<UsersDataSet>() {

            public UsersDataSet handle(ResultSet result) throws SQLException {
                if(!result.next()) {
                    return null;
                } else {
                    return new UsersDataSet(result.getLong(1),result.getString(2),result.getString(3), false);
                }
            }
        });
    }
    public void setNewUser(String name, String password) throws SQLException {
        PreparedExecutor preparedExecutor = new PreparedExecutor();
        preparedExecutor.execUpdate(con, name, password);
    }
}
