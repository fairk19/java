package gameMechanics;


import base.*;
import accountService.Address;
import messageSystem.MessageSystemImpl;
import utils.LoggerFactory;
import utils.TimeHelper;
import java.util.logging.Logger;

/**
 * Created with IntelliJ IDEA.
 * User: Alexandr
 * Date: 30.11.13
 * Time: 14:32
 * To change this template use File | Settings | File Templates.
 */
public class GameMechanicsImpl implements Runnable, Abonent, GameMechanics {

    private static Logger logger = LoggerFactory.getLogger("GameMechanicsLogger", "./log/gameMechanics-log.txt");
    private MessageSystem ms;
    private Address gameMechanicsAddress;
    private GameSession gameSession;

    public GameMechanicsImpl(MessageSystemImpl ms) {

        logger.info("GameMechanics created");
        this.ms = ms;
        this.gameMechanicsAddress = new Address();
        ms.addService(this);
        ms.getAddressService().setGameMechanics(gameMechanicsAddress);
    }

    public Address getAddress() {
        return gameMechanicsAddress;
    }

    public void run(){
        while(true)
        {
            ms.execForAbonent(this);
            long startTime = System.currentTimeMillis();
            long deltaTime = System.currentTimeMillis() - startTime;
            if(deltaTime/ Frontend.TICK_TIME < 1)
                TimeHelper.sleep((int)(Frontend.TICK_TIME - deltaTime));
        }
    }

    public void startGame() {

        if (gameSession == null){
            gameSession = new GameSession(ms.getAddressService());
        }
    }
}