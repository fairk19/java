package resourceSystem;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

/**
 * Created by alexandr on 29.01.14.
 */
public class ResourceFactory {

    public static Object readResourse(String path) {
        try{
            FileInputStream fileOut = new FileInputStream(path);
            ObjectInputStream in = new ObjectInputStream(fileOut);
            Object object = in.readObject();
            in.close();
            return object;
        }catch(IOException i){
            i.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
