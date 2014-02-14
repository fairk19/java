package base;

import java.util.Iterator;

/**
 * Created by alexandr on 29.01.14.
 */
public interface VFS {
    boolean isExist(String path);

    boolean isDirectory(String path);

    String getAbsolutePath(String file);

    byte[] getBytes(String file);

    String getUFT8Text(String file);

    Iterator<String> getIterator(String startDir);
}
