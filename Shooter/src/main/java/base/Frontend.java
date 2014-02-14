package base;

/**
 * Created with IntelliJ IDEA.
 * User: alexandr
 * Date: 04.11.13
 * Time: 18:14
 * To change this template use File | Settings | File Templates.
 */
public interface Frontend extends Abonent,Runnable {

        int TICK_TIME = 100;
        String homePageURL = "/*";
        String playPageURL = "/play/";
        void setId(String sessionId, Long userId);
        void setRegStatus(String sessionId, Boolean status);
}
