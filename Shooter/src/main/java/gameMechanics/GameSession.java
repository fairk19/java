package gameMechanics;

import accountService.Address;
import accountService.AddressService;
import utils.LoggerFactory;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created with IntelliJ IDEA.
 * User: Alexandr
 * Date: 30.11.13
 * Time: 14:42
 * To change this template use File | Settings | File Templates.
 */
public class GameSession {

    private final int maxCountUsers = 3;
    private Map<Integer, UserGameSession> userIdToUserGameSession = new HashMap<Integer, UserGameSession>();
    private ArrayList<GameSocketImpl> users = new ArrayList<GameSocketImpl>();
    private ArrayList<Integer> userIds = new ArrayList<Integer>();
    private ConcurrentHashMap<Integer, GameSocketImpl> userIdToSocket = new ConcurrentHashMap<Integer, GameSocketImpl>();
    private GameMap gameMap;
    private Address gameWebSockets;
    private static Logger logger = LoggerFactory.getLogger("GameSessionLogger", "./log/gameSession-log.txt");
    private static final GameSession INSTANCE = new GameSession();

    public GameSession(){}

    public GameSession(AddressService addressService){

        logger.info("Game session created");
        this.gameWebSockets = addressService.getWebSockets();

        gameMap = new GameMap();
        logger.info("Game map created");
    }

    public UserGameSession getUserGameSession(Integer userId){
        return userIdToUserGameSession.get(userId);
    }
    public Address getAddressGameWebSockets() {
        return gameWebSockets;
    }
    public GameMap getGameMap(){
        return gameMap;
    }
    public static GameSession getInstance()
    {
        return INSTANCE;
    }
    public void addUserInGame(Integer userId, GameSocketImpl socket)
    {
        users.add(socket);
        userIds.add(userId);
        userIdToSocket.put(userId, socket);

        if(userIdToUserGameSession.size() <= maxCountUsers && !userIdToUserGameSession.containsKey(userId)){

            userIdToUserGameSession.put(userId, new UserGameSession(userId));
            logger.info("User "+ userId +" add in game session");
            System.out.println("User "+ userId +" add in game session");
        }
        if(userIdToUserGameSession.size() > maxCountUsers) {

            logger.log(Level.WARNING, "Maximum number of players");
            System.out.println("Maximum number of players");
            return;
        }
    }
    public void deleteUser(Integer userId, GameSocketImpl socket)
    {
        users.remove(socket);
        userIds.remove(userId);
//        userIdToSocket.remove(userId);
//        userIdToUserGameSession.remove(userId);
//        System.out.println("Destroy user " + userId);
    }
    public void sendMessageAllUsers(String message)
    {
        for (GameSocketImpl gameSocketImpl : users)
        {
            gameSocketImpl.session.getRemote().sendStringByFuture(message);
        }
    }
    public void sendMessageUser(Integer userId, String message)
    {
        GameSocketImpl userSocket = findUserSocket(userId);
        userSocket.session.getRemote().sendStringByFuture(message);
    }
    private final GameSocketImpl findUserSocket(Integer userId)
    {
        return userIdToSocket.get(userId);
    }

    public String messageCreateSprites(UserCommand userCommand)
    {
        String messageCreateSprites = userCommand.createSpritesJSONMessage(this.userIdToUserGameSession, this.userIds);
        return messageCreateSprites;
    }
}
