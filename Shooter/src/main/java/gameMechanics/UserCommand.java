package gameMechanics;


import org.json.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import utils.ObjCommand;
import utils.TypeCommand;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created by Alexandr on 19.01.14.
 */
public class UserCommand {

    private String type;
    private Integer fromUserId;
    private String obj;

    public void parseUserJSONMessage(String jsonMessage)
    {

        try
        {
            JSONParser parser = new JSONParser();
            Object obj = parser.parse(jsonMessage);
            JSONObject jsonObj = (JSONObject) obj;
            this.fromUserId = Integer.parseInt(jsonObj.get("from").toString());
            this.type = jsonObj.get("command").toString();
            this.obj = jsonObj.get("obj").toString();

        }
        catch (ParseException e)
        {
            System.out.println("gameMechanics:: UserCommand:: PARSE ERROR");
        }
    }

    public String createMapJSONMessage(GameMap gameMap)
    {
        JSONObject jsonObj = new JSONObject();
        JSONObject value = new JSONObject();

        value.put("width", gameMap.getWidth());
        value.put("height", gameMap.getHeight());
        value.put("tileWidth", gameMap.getTileWidth());
        value.put("tileHeight", gameMap.getTileHeight());

        jsonObj.put("from", "server");
        jsonObj.put("command", TypeCommand.getCreate());
        jsonObj.put("obj", ObjCommand.getMap());
        jsonObj.put("value", value);

        return jsonObj.toString();
    }

    public String createUserJSONMessage(UserGameSession userGameSession)
    {
        JSONObject jsonObj = new JSONObject();
        JSONObject value = new JSONObject();

        value.put("userId", this.fromUserId);
        value.put("posX", userGameSession.getX());
        value.put("posY", userGameSession.getY());

        jsonObj.put("from", "server");
        jsonObj.put("command", TypeCommand.getCreate());
        jsonObj.put("obj", ObjCommand.getUser());

        jsonObj.put("value", value);

        return jsonObj.toString();
    }

    public String createSpritesJSONMessage(Map<Integer, UserGameSession> userIdToUserGameSession, ArrayList<Integer> userIds)
    {
        JSONObject jsonObj = new JSONObject();
        JSONArray value = new JSONArray();

        for(int i = 0; i < userIds.size(); i++)
        {
            UserGameSession userGameSession = userIdToUserGameSession.get(userIds.get(i));

            JSONObject sprite = new JSONObject();
            sprite.put("userId", userIds.get(i));
            sprite.put("posX", userGameSession.getX());
            sprite.put("posY", userGameSession.getY());
            value.put(sprite);
        }

        jsonObj.put("from", "server");
        jsonObj.put("command", TypeCommand.getCreate());
        jsonObj.put("obj", ObjCommand.getSprite());
        jsonObj.put("size", userIdToUserGameSession.size());
        jsonObj.put("value", value);

        return jsonObj.toString();
    }
    public String getType()
    {
        return this.type;
    }

    public Integer getFromUserId()
    {
        return this.fromUserId;
    }

    public String getObj()
    {
        return this.obj;
    }
}
