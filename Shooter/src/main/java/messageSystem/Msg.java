package messageSystem;

import base.Abonent;
import accountService.Address;

/**
 * Created with IntelliJ IDEA.
 * User: alexandr
 * Date: 12.10.13
 * Time: 10:47
 * To change this template use File | Settings | File Templates.
 */
public abstract class Msg {
    private Address from;
    private Address to;

    public Msg(Address from, Address to){
        this.from = from;
        this.to = to;
    }

    protected Address getFrom(){
        return from;
    }

    protected Address getTo(){
        return to;
    }

    public abstract void exec(Abonent abonent);
}