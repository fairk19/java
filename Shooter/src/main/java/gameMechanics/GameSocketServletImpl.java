package gameMechanics;


import accountService.Address;
import base.Frontend;
import base.GameSocketServlet;
import base.MessageSystem;
import org.eclipse.jetty.websocket.servlet.WebSocketServlet;
import org.eclipse.jetty.websocket.servlet.WebSocketServletFactory;
import utils.LoggerFactory;
import utils.TimeHelper;

import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Logger;

/**
 * Created with IntelliJ IDEA.
 * User: Alexandr
 * Date: 02.12.13
 * Time: 2:10
 * To change this template use File | Settings | File Templates.
 */
public class GameSocketServletImpl extends WebSocketServlet implements GameSocketServlet {

    private MessageSystem ms;
    private Address gameSocketImplAddress;
    private Address gameMechanicsAddress;
    private static Logger logger = LoggerFactory.getLogger("GameSocketLogger", "./log/gameSocket-log.txt");

    public GameSocketServletImpl(){}
    public GameSocketServletImpl(MessageSystem messageSystem){

        logger.info("GameSocketServlet created");
        this.ms = messageSystem;
        this.gameSocketImplAddress = new Address();
        messageSystem.addService(this);
        ms.getAddressService().setWebSockets(gameSocketImplAddress);
    }

    public Address getAddress() {
        return gameSocketImplAddress;
    }
    public void run() {
        while(true)
        {
            ms.execForAbonent(this);
            long startTime = System.currentTimeMillis();
            long deltaTime = System.currentTimeMillis() - startTime;
            if(deltaTime/ Frontend.TICK_TIME < 1)
                TimeHelper.sleep((int) (Frontend.TICK_TIME - deltaTime));
        }
    }
    public void setGameMechanicsAddress(Address gameMechanicsAddress)
    {
        this.gameMechanicsAddress = gameMechanicsAddress;
    }

    @Override
    public void configure(WebSocketServletFactory factory)
    {
        factory.register(GameSocketImpl.class);
    }
}
