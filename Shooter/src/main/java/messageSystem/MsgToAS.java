package messageSystem;

import base.Abonent;
import base.AccountService;
import accountService.Address;

/**
 * Created with IntelliJ IDEA.
 * User: alexandr
 * Date: 12.10.13
 * Time: 10:50
 * To change this template use File | Settings | File Templates.
 */
public abstract class MsgToAS extends Msg {
    public MsgToAS(Address from, Address to) {
        super(from, to);
    }

    public void exec(Abonent abonent) {
        if(abonent instanceof AccountService){
            exec((AccountService) abonent);
        }
    }

    public abstract void exec(AccountService accountService);
}