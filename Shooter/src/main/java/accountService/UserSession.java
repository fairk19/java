package accountService;

import utils.SessionStatus;
import java.util.concurrent.atomic.AtomicReference;

/**
 * Created with IntelliJ IDEA.
 * User: alexandr
 * Date: 12.10.13
 * Time: 10:12
 * To change this template use File | Settings | File Templates.
 */
public class UserSession {

    private Address accountService;
    private Address gameMechanics;
    private String name;
    private String sessionId;
    private Long userId;
    private boolean bdIsReady;
    private Boolean isReady = false;
    private Boolean isAlive = false;
    public AtomicReference<SessionStatus> sessionStatus = new AtomicReference<SessionStatus>(SessionStatus.eInProcess);

    public UserSession(String sessionId, String name, boolean bdIsReady, AddressService addressService) {
        this.sessionId = sessionId;
        this.name = name;
        this.bdIsReady = bdIsReady;
        this.accountService = addressService.getAccountService();
        this.accountService = addressService.getAccountService();
        this.gameMechanics = addressService.getGameMechanics();
    }

    public Address getAccountService()
    {
        return accountService;
    }
    public Address getGameMechanics() { return  gameMechanics; }

    public Long getUserId(){
        return userId;
    }
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }
    public boolean getBdIsReady() {
        return bdIsReady;
    }
    public void setBdIsReady(boolean bdIsReady) {
        this.bdIsReady =  bdIsReady;
    }
    public void setName(String name) {
        this.name = name;
    }

}