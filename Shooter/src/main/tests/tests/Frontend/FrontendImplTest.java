package tests.Frontend;

import accountService.Address;
import com.sun.istack.internal.NotNull;
import frontend.FrontendImpl;
import junit.framework.TestCase;
import messageSystem.MessageSystemImpl;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.openqa.selenium.support.ui.WebDriverWait;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;

/**
 * Created with IntelliJ IDEA.
 * User: alexandr
 * Date: 25.10.13
 * Time: 18:37
 * To change this template use File | Settings | File Templates.
 */

@RunWith(MockitoJUnitRunner.class)
public class FrontendImplTest extends TestCase {

    private HttpServletRequest request = mock(HttpServletRequest.class);
    private HttpServletResponse response = mock(HttpServletResponse.class);
    private HttpSession session = mock(HttpSession.class);
    private FrontendImpl frontendImpl = new FrontendImpl(new MessageSystemImpl());

    @Before
    public void setUp() throws Exception {

        when(response.getWriter()).thenReturn(new PrintWriter("Answer ok!"));
        when(request.getSession()).thenReturn(session);
        when(session.getId()).thenReturn("IdTest");
        when(request.getPathInfo()).thenReturn("/userid");
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testGetAddress() throws Exception {
        assertNotNull(frontendImpl.getAddress());
        assertSame((frontendImpl.getAddress()).getClass(),(Address.class));
        System.out.println("Тест адресса - ОК!");
    }

    @Ignore
    @Test
    public void seleniumTest() throws Exception {
        WebDriver driver = new HtmlUnitDriver(true);
        driver.get("http://localhost:8083/");
        WebElement elementLogin = driver.findElement(By.name("login"));
        elementLogin.sendKeys("user1");
        WebElement elementPassword = driver.findElement(By.name("password"));
        elementPassword.sendKeys("user1");
        WebElement elementForm = driver.findElement(By.className("form-signin"));
        elementForm.submit();

        (new WebDriverWait(driver, 1000)).until(new ExpectedCondition<Boolean>() {
            @Override
            @NotNull
            public Boolean apply(@NotNull WebDriver d) {
                WebElement elementId = d.findElement(By.className("userState"));
                System.out.println(elementId.getText());
                return true;
            }
        });
        driver.quit();
    }

}
