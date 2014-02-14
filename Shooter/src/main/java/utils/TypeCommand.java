package utils;

import java.util.Map;

/**
 * Created by Игорь on 01.02.14.
 */
public class TypeCommand {

    private static final String create = "create";
    private static final String move = "move";
    private static final String destroy = "destroy";
    private static final String update = "update";

    public static final String getCreate()
    {
        return create;
    }

    public static final String getMove()
    {
        return move;
    }

    public  static final String getDestroy()
    {
        return destroy;
    }

    public static final String getUpdate()
    {
        return destroy;
    }
}
