package base;

/**
 * Created with IntelliJ IDEA.
 * User: alexandr
 * Date: 09.11.13
 * Time: 20:28
 * To change this template use File | Settings | File Templates.
 */
public interface AccountService extends Runnable {
    public static final Long ErrorUserPassword = -1L;
    public static final Long ErrorUserLogin = -2L;
    public static final Long ErrorUserAlreadyLogged = -3L;

    void  setNewUserInDb(String name, String password);
    Long getUserId(String name, String password);
    MessageSystem getMessageSystem();
}
