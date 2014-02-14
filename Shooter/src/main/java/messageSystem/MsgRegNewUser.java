package messageSystem;

import accountService.Address;
import base.AccountService;

/**
 * Created with IntelliJ IDEA.
 * User: vanik
 * Date: 20.12.13
 * Time: 17:42
 * To change this template use File | Settings | File Templates.
 */
public class MsgRegNewUser extends MsgToAS {
    private String name;
    private String password;
    private String sessionId;

    public MsgRegNewUser(Address from, Address to, String name, String password, String sessionId) {
        super(from, to);
        this.name = name;
        this.password = password;
        this.sessionId = sessionId;
    }
    public void exec(AccountService accountServiceImpl) {
        accountServiceImpl.setNewUserInDb(name, password);
        accountServiceImpl.getMessageSystem().sendMessage(new MsgUpdateRegNewUser(getTo(), getFrom(), sessionId, 1L));
    }


}
