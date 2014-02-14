package accountService;

/**
 * Created with IntelliJ IDEA.
 * User: alexandr
 * Date: 29.09.13
 * Time: 14:49
 * To change this template use File | Settings | File Templates.
 */
public class User {
    private String username, password;
    private int userId;
    private Long userSessionId;

    User(String _username, String _password, int _userId) {
        username = _username;
        password = _password;
        userId = _userId;
    }
}
