package tests.MessageSystem;

import accountService.*;
import base.AccountService;
import base.Frontend;
import frontend.FrontendImpl;
import junit.framework.TestCase;
import messageSystem.*;
import org.junit.Before;
import org.junit.Test;

/**
 * Created with IntelliJ IDEA.
 * User: Alexandr
 * Date: 29.11.13
 * Time: 18:42
 * To change this template use File | Settings | File Templates.
 */
public class MessageSystemImplTest extends TestCase {

    private MessageSystemImpl ms = new MessageSystemImpl();
    private Frontend frontendImpl = new FrontendImpl(ms);
    private AccountServiceImpl accountServiceImpl = new AccountServiceImpl(ms);

    public class MsgTest extends MsgToAS {

        private String message;

        public MsgTest(Address from, Address to, String message) {
            super(from, to);
            this.message = message;
        }

        public void exec(AccountService accountServiceImpl) {
            System.out.print(message);
        }

    }

    @Before
    public void setUp() throws Exception {
        (new Thread(frontendImpl)).start();
        (new Thread(accountServiceImpl)).start();
    }

    @Test
    public void testGetAddressService() throws Exception {
        assertNotNull(ms.getAddressService());
        assertSame(ms.getAddressService().getClass(), AddressService.class);
        System.out.println("Тест адресса сервиса - ОК!");

        ms.sendMessage(new MsgTest(frontendImpl.getAddress(), accountServiceImpl.getAddress(), "Привет, я тестовое сообщение!"));
    }
}
