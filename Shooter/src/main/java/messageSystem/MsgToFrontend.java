package messageSystem;

import base.Abonent;
import accountService.Address;
import base.Frontend;

/**
 * Created with IntelliJ IDEA.
 * User: alexandr
 * Date: 12.10.13
 * Time: 10:51
 * To change this template use File | Settings | File Templates.
 */
public abstract class MsgToFrontend extends Msg {
    public MsgToFrontend(Address from, Address to) {
        super(from, to);
    }

    public void exec(Abonent abonent) {
        if(abonent instanceof Frontend){
            exec((Frontend)abonent);
        }
    }

    abstract void exec(Frontend frontend);
}
