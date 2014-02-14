package gameMechanics;

/**
 * Created with IntelliJ IDEA.
 * User: Alexandr
 * Date: 06.12.13
 * Time: 19:32
 * To change this template use File | Settings | File Templates.
 */
public class GameMap {

    private static String width;
    private static String height;
    private static String tileWidth;
    private static String tileHeight;

    public GameMap(){}
    public static Integer getWidth()
    {
        return Integer.parseInt(width);
    }
    public static Integer getHeight()
    {
        return Integer.parseInt(height);
    }
    public static Integer getTileWidth()
    {
        return  Integer.parseInt(tileWidth);
    }
    public static Integer getTileHeight()
    {
        return Integer.parseInt(tileHeight);
    }

}
