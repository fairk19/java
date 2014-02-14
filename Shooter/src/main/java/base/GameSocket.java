package base;

import accountService.Address;
import base.Abonent;
import org.json.simple.JSONObject;

/**
 * Created with IntelliJ IDEA.
 * User: Alexandr
 * Date: 01.12.13
 * Time: 20:23
 * To change this template use File | Settings | File Templates.
 */
public interface GameSocket extends Runnable, Abonent {
    void setGameMechanicsAddress(Address gameMechanicsAddress);
    void createMap(JSONObject propertyCreateGameMap);
    void addUserInGame(Long userId);
}
