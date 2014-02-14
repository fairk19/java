package messageSystem;

import base.Abonent;
import accountService.Address;
import accountService.AddressService;
import base.MessageSystem;

import java.util.HashMap;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Created with IntelliJ IDEA.
 * User: alexandr
 * Date: 12.10.13
 * Time: 10:47
 * To change this template use File | Settings | File Templates.
 */
public class MessageSystemImpl implements MessageSystem {

    private Map<Address, ConcurrentLinkedQueue<Msg>> messages = new HashMap<Address, ConcurrentLinkedQueue<Msg>>();
    private AddressService addressService = new AddressService();

    public void addService(Abonent abonent){
        messages.put(abonent.getAddress(), new ConcurrentLinkedQueue<Msg>());
    }

    public void sendMessage(Msg message){
        Queue<Msg> messageQueue = messages.get(message.getTo());
        messageQueue.add(message);
    }

    public void execForAbonent(Abonent abonent) {
        Queue<Msg> messageQueue = messages.get(abonent.getAddress());
        if(messageQueue == null){
            return;
        }
        while(!messageQueue.isEmpty()){
            Msg message = messageQueue.poll();
            message.exec(abonent);
        }
    }

    public AddressService getAddressService(){
        return addressService;
    }
}
