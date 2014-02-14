package base;

import base.Abonent;
import accountService.AddressService;
import messageSystem.Msg;

/**
 * Created with IntelliJ IDEA.
 * User: alexandr
 * Date: 09.11.13
 * Time: 20:19
 * To change this template use File | Settings | File Templates.
 */
public interface MessageSystem {
    void addService(Abonent abonent);
    void sendMessage(Msg message);
    void execForAbonent(Abonent abonent);
    AddressService getAddressService();
}
