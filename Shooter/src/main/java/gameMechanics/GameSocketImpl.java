package gameMechanics;

import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.WebSocketAdapter;
import org.json.JSONArray;
import org.json.simple.JSONObject;
import utils.ObjCommand;
import utils.TypeCommand;

/**
 * Created by alexandr on 02.01.14.
 */
public class GameSocketImpl extends WebSocketAdapter {

    public Session session;
    private Integer userId;

    @Override
    public void onWebSocketConnect(Session session)
    {
        super.onWebSocketConnect(session);
        this.session = session;
    }

    @Override
    public void onWebSocketText(String message)
    {
        super.onWebSocketText(message);
        System.out.println("Received TEXT message: " + message);
        UserCommand userCommand = new UserCommand();
        userCommand.parseUserJSONMessage(message);

        if(userCommand.getType().equals("play"))
        {
            this.userId = userCommand.getFromUserId();
            GameSession.getInstance().addUserInGame(this.userId, this);
            GameMap gameMap = GameSession.getInstance().getGameMap();
            String messageCreateMap = userCommand.createMapJSONMessage(gameMap);
            GameSession.getInstance().sendMessageUser(this.userId, messageCreateMap);

            UserGameSession userGameSession = GameSession.getInstance().getUserGameSession(this.userId);
            String messageCreateUser = userCommand.createUserJSONMessage(userGameSession);
            GameSession.getInstance().sendMessageUser(this.userId, messageCreateUser);

            String messageCreateSprites = GameSession.getInstance().messageCreateSprites(userCommand);
            GameSession.getInstance().sendMessageAllUsers(messageCreateSprites);
        }
    }

    @Override
    public void onWebSocketClose(int statusCode, String reason)
    {
        super.onWebSocketClose(statusCode,reason);
        GameSession.getInstance().deleteUser(this.userId, this);
    }

    @Override
    public void onWebSocketError(Throwable cause)
    {
        super.onWebSocketError(cause);
        cause.printStackTrace(System.err);
    }
}
