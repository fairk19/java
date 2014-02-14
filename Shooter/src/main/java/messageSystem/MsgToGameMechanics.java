package messageSystem;

import base.Abonent;
import accountService.Address;
import base.GameMechanics;


/**
 * Created with IntelliJ IDEA.
 * User: Alexandr
 * Date: 30.11.13
 * Time: 15:11
 * To change this template use File | Settings | File Templates.
 */
public abstract class MsgToGameMechanics extends Msg {

    public MsgToGameMechanics(Address from, Address to) {
        super(from, to);
    }

    public void exec(Abonent abonent) {
        if(abonent instanceof GameMechanics){
            exec((GameMechanics)abonent);
        }
    }
    public abstract void exec(GameMechanics gameMechanics);
}
