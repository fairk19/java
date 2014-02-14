package tests.PageGenerator;

import junit.framework.TestCase;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import frontend.PageGenerator;

import java.util.HashMap;
import java.util.Map;

import static frontend.FrontendImpl.getTime;

/**
 * Created with IntelliJ IDEA.
 * User: alexandr
 * Date: 26.10.13
 * Time: 9:29
 * To change this template use File | Settings | File Templates.
 */

public class PageGeneratorTest extends TestCase {

    private Map<String, Object> pageVariables = new HashMap<>();
    private String userState = "userState";

    @Before
    public void setUp() throws Exception {
        pageVariables.put("refreshPeriod", "1000");
        pageVariables.put("serverTime", getTime());
        pageVariables.put("userState", userState);
    }

    @After
    public void tearDown() throws Exception {

    }
    @Test
    public void testGetPage() throws Exception {
        assertTrue(PageGenerator.getPage("userid.tml", pageVariables).contains("userState"));
    }
}
