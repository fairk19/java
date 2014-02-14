package messageSystem;

import base.AccountService;
import accountService.Address;

/**
 * Created with IntelliJ IDEA.
 * User: alexandr
 * Date: 12.10.13
 * Time: 10:49
 * To change this template use File | Settings | File Templates.
 */
public class MsgGetUserId extends MsgToAS {
        private String name;
        private String password;
        private String sessionId;

        public MsgGetUserId(Address from, Address to, String name, String password, String sessionId) {
            super(from, to);
            this.password = password;
            this.name = name;
            this.sessionId = sessionId;
        }

        public void exec(AccountService accountServiceImpl) {
            Long id = accountServiceImpl.getUserId(name, password);
            accountServiceImpl.getMessageSystem().sendMessage(new MsgUpdateUserId(getTo(), getFrom(), sessionId, id));
        }
}
