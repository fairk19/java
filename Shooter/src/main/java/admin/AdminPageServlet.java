package admin;

import utils.TimeHelper;
import frontend.PageGenerator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: alexandr
 * Date: 02.11.13
 * Time: 10:23
 * To change this template use File | Settings | File Templates.
 */
public class AdminPageServlet extends HttpServlet {
    public static final String adminPageURL = "/admin";

    public void doGet(HttpServletRequest request,
                      HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        response.setStatus(HttpServletResponse.SC_OK);
        Map<String, Object> pageVariables = new HashMap<>();
        String timeString = request.getParameter("shutdown");

        if (timeString != null) {
            int timeMS = Integer.valueOf(timeString);
            new Thread(new ShutdownTask(timeMS)).start();


            pageVariables.put("status", "Server will be down after: " + timeMS + " ms");
            response.getWriter().println(PageGenerator.getPage("admin.tml", pageVariables));
            return;
        }
        pageVariables.put("status", "run");
        response.getWriter().println(PageGenerator.getPage("admin.tml", pageVariables));
    }

    private class ShutdownTask implements Runnable {
        int sleepTime;

        public ShutdownTask(int sleepTime) {
            this.sleepTime = sleepTime;
        }

        @Override
        public void run() {
            TimeHelper.sleep(sleepTime);
            System.out.append("Shutdown requested");
            System.exit(0);
        }
    }
}
