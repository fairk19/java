package accountService;

/**
 * Created with IntelliJ IDEA.
 * User: alexandr
 * Date: 12.10.13
 * Time: 10:38
 * To change this template use File | Settings | File Templates.
 */
public class AddressService {
    private Address accountService;
    private Address gameMechanics;
    private Address webSockets;

    public Address getAccountService() {
        return accountService;
    }
    public Address getGameMechanics() {return  gameMechanics; }
    public Address getWebSockets() { return webSockets; }

    public void setAccountService(Address accountService) {
        this.accountService = accountService;
    }
    public void setGameMechanics(Address gameMechanics) { this.gameMechanics = gameMechanics; }
    public void setWebSockets(Address webSockets) { this.webSockets = webSockets; }
}