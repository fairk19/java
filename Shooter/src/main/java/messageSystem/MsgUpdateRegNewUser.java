package messageSystem;

import accountService.Address;
import base.Frontend;

/**
 * Created with IntelliJ IDEA.
 * User: vanik
 * Date: 20.12.13
 * Time: 17:58
 * To change this template use File | Settings | File Templates.
 */
public class MsgUpdateRegNewUser extends MsgToFrontend{
    private long reqBD;
    private String sessionId;

    public  MsgUpdateRegNewUser(Address from, Address to,String sessionId, Long reqBD) {
        super(from, to);
        this.reqBD = reqBD;
        this.sessionId = sessionId;
    }
    void exec(Frontend frontend) {
        frontend.setRegStatus(sessionId, true);
    }


}
