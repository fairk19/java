package accountService;

/**
 * Created with IntelliJ IDEA.
 * User: vanik
 * Date: 11.12.13
 * Time: 23:16
 * To change this template use File | Settings | File Templates.
 */

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
public class MakeConnection {

    private static String hostName;
    private static String port;
    private static String dbName;
    private static String login;
    private static String password;

    public static Connection getConnection() {
        try{
            DriverManager.registerDriver((Driver) Class.forName("com.mysql.jdbc.Driver").newInstance());

            StringBuilder url = new StringBuilder();

            url.
                    append("jdbc:mysql://").		//db type
                    append(hostName + ":"). 	    //host name
                    append(port + "/").				//port
                    append(dbName + "?").			//db name
                    append("user=" + login +"&").   //login
                    append("password=" + password);	//password


            Connection connection = DriverManager.getConnection(url.toString());
            return connection;
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void connect(){
        Connection connection = getConnection();
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
