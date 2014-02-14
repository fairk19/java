package utils;

/**
 * Created with IntelliJ IDEA.
 * User: alexandr
 * Date: 12.10.13
 * Time: 10:48
 * To change this template use File | Settings | File Templates.
 */
public class TimeHelper {
    public static void sleep(int period){
        try{
            Thread.sleep(period);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void sleep(){
        try{
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
