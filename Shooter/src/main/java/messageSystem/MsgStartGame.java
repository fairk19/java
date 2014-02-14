package messageSystem;

import accountService.Address;
import base.GameMechanics;

/**
 * Created by Игорь on 03.02.14.
 */
public class MsgStartGame extends MsgToGameMechanics {

    public MsgStartGame(Address from, Address to){
        super(from, to);
    }

    public void exec(GameMechanics gameMechanics){
        gameMechanics.startGame();
    }
}
