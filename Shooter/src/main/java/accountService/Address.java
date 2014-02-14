package accountService;

/**
 * Created with IntelliJ IDEA.
 * User: alexandr
 * Date: 12.10.13
 * Time: 10:36
 * To change this template use File | Settings | File Templates.
 */
import java.util.concurrent.atomic.AtomicInteger;

public class Address {
    static private AtomicInteger abonentIdCreator = new AtomicInteger();
    final private int abonentId;

    public Address(){
        this.abonentId = abonentIdCreator.incrementAndGet();
    }

    public int hashCode(){
        return abonentId;
    }
}