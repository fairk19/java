package messageSystem;

import accountService.Address;
import base.Abonent;
import base.GameSocketServlet;

/**
 * Created with IntelliJ IDEA.
 * User: Alexandr
 * Date: 06.12.13
 * Time: 18:17
 * To change this template use File | Settings | File Templates.
 */
public abstract class MsgToGameSocket extends Msg {

    public MsgToGameSocket(Address from, Address to) {
        super(from, to);
    }

    public void exec(Abonent abonent) {
        if(abonent instanceof GameSocketServlet){
            exec((GameSocketServlet)abonent);
        }
    }
    public abstract void exec(GameSocketServlet gameSocketServlet);
}
