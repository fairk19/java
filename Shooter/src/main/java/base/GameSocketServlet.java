package base;

import accountService.Address;

/**
 * Created with IntelliJ IDEA.
 * User: Alexandr
 * Date: 01.12.13
 * Time: 20:23
 * To change this template use File | Settings | File Templates.
 */
public interface GameSocketServlet extends Runnable, Abonent {
    void setGameMechanicsAddress(Address gameMechanicsAddress);
}
