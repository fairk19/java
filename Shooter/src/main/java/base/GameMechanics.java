package base;

import base.Abonent;

/**
 * Created with IntelliJ IDEA.
 * User: Alexandr
 * Date: 30.11.13
 * Time: 15:15
 * To change this template use File | Settings | File Templates.
 */
public interface GameMechanics extends Abonent, Runnable {
    void startGame();
}
