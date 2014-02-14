package frontend;

import accountService.Address;
import accountService.UserSession;
import base.Abonent;
import base.AccountService;
import base.Frontend;
import base.MessageSystem;
import messageSystem.*;
import utils.LoggerFactory;
import utils.SessionStatus;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.Logger;

/**
 * Created with IntelliJ IDEA.
 * User: alexandr
 * Date: 21.09.13
 * Time: 10:47
 * To change this template use File | Settings | File Templates.
 */

public class FrontendImpl extends HttpServlet implements Runnable, Abonent, Frontend {

    private Logger loggerFrontEnd = LoggerFactory.getLogger("FrontendLogger", "./log/frontend-log.txt");
    private Logger loggerDB = LoggerFactory.getLogger("dataBaseLogger", "./log/dataBase-log.txt");
    private MessageSystem ms;
    private Address addressFrontend;
    private Map<String, Boolean> loginToIsOnline = new HashMap<>();
    private Map<String, Boolean> sessionIdToRegStatus = new HashMap<>();
    private Map<String, Address> sessionIdToAccountServiceAddress = new HashMap<>();
    private Map<String, UserSession> sessionIdToUserSession = new HashMap<>();

    public FrontendImpl(MessageSystemImpl ms) {
        loggerFrontEnd.info("Frontend created");
        this.ms = ms;
        this.addressFrontend = new Address();
        ms.addService(this);
    }

    public static String getTime() {
        Date date = new Date();
        date.getTime();
        DateFormat formatter = new SimpleDateFormat("HH.mm.ss");
        return formatter.format(date);
    }

    public Address getAddress()
    {
        return addressFrontend;
    }

    public void run() {
        while(true)
        {
            ms.execForAbonent(this);
        }
    }

    public void setId(String sessionId, Long userId) {
        UserSession userSession = sessionIdToUserSession.get(sessionId);
        userSession.setUserId(userId);
        userSession.setBdIsReady(true);
    }
    public void setRegStatus(String sessionId, Boolean status) {
        sessionIdToRegStatus.put(sessionId,status);
    }

    public void doGet(HttpServletRequest request,
                      HttpServletResponse response) throws ServletException, IOException {
        loggerFrontEnd.info("doGet " + request.toString());

        if (request.getPathInfo().equals("/logout")) {
            response.setContentType("text/html;charset=utf-8");
            response.setStatus(HttpServletResponse.SC_OK);

            HttpSession session = request.getSession();
            UserSession userSession = sessionIdToUserSession.get(session.getId());

            loginToIsOnline.remove(userSession.getName());
            sessionIdToUserSession.remove(session.getId());

            response.sendRedirect("/home");
        }

        if (request.getPathInfo().equals("/home")) {

            response.setContentType("text/html;charset=utf-8");
            response.setStatus(HttpServletResponse.SC_OK);
            String sessionId = request.getSession().getId();

            if(sessionIdToUserSession.get(sessionId) == null) {
                UserSession userSession = new UserSession(sessionId, "annonimus",false, ms.getAddressService());
                sessionIdToUserSession.put(sessionId, userSession);
            }
            if(sessionIdToUserSession.get(sessionId).getName().equals("annonimus")) {
                Map<String, Object> pageVariables = new HashMap<>();
                pageVariables.put("sessionId",sessionId);
                response.getWriter().println(PageGenerator.getPage("mainPage.tml", pageVariables));
            }
            else {
                Map<String, Object> pageVariables = new HashMap<>();
                pageVariables.put("sessionId",sessionId);
                pageVariables.put("userName", sessionIdToUserSession.get(sessionId).getName());
                response.getWriter().println(PageGenerator.getPage("mainPageForRegUsers.tml", pageVariables));
            }
        }

        if (request.getPathInfo().equals("/registration")) {
            response.setContentType("text/html;charset=utf-8");
            response.setStatus(HttpServletResponse.SC_OK);

            HttpSession session = request.getSession();
            Map<String, Object> pageVariables = new HashMap<>();
            pageVariables.put("sessionId",session.getId());
            response.getWriter().println(PageGenerator.getPage("registrationForm.tml", pageVariables));
        }

        if (request.getPathInfo().equals("/game")) {

            response.setContentType("text/html;charset=utf-8");
            response.setStatus(HttpServletResponse.SC_OK);
            UserSession userSession = sessionIdToUserSession.get(request.getSession().getId());

            if(userSession == null || request.getSession() == null){
                response.sendRedirect("/home");
                return;
            }

            responseGamePage(response, userSession.getUserId());
        }
    }

    public static void responseGamePage(HttpServletResponse response, Long userId) throws  IOException {
        Map<String, Object> pageVariables = new HashMap<>();
        pageVariables.put("userId", userId);
        response.getWriter().println(PageGenerator.getPage("game.tml", pageVariables));
    }

    public void doPost(HttpServletRequest request,
                       HttpServletResponse response) throws ServletException, IOException {
        if (request.getPathInfo().equals("/login")) {

            String login = request.getParameter("login");
            String password = request.getParameter("password");
            String sessionId = request.getSession().getId();
            UserSession userSession = sessionIdToUserSession.get(sessionId);
            response.setContentType("text/html;charset=utf-8");
            response.setStatus(HttpServletResponse.SC_OK);

            Address accountServiceAddress = userSession.getAccountService();
            userSession.setBdIsReady(false);
            ms.sendMessage(new MsgGetUserId(this.addressFrontend, accountServiceAddress, login, password, sessionId));
            boolean bdIsReady = false;
            while(!bdIsReady) {
                UserSession userSessionForCheck = sessionIdToUserSession.get(sessionId);
                bdIsReady = userSessionForCheck.getBdIsReady();
            }
            UserSession updatedUserSession = sessionIdToUserSession.get(sessionId);

            if (updatedUserSession == null) {
                response.getWriter().write("Что-то пошло не так");
            }
            else if(updatedUserSession.getUserId() == AccountService.ErrorUserLogin) {

                response.getWriter().write(AccountService.ErrorUserLogin.toString());

            }
            else if(updatedUserSession.getUserId() == AccountService.ErrorUserPassword) {

                response.getWriter().write(AccountService.ErrorUserPassword.toString());

            } else if(loginToIsOnline.get(login) != null && loginToIsOnline.get(login).equals(Boolean.TRUE)) {

                    response.getWriter().write(AccountService.ErrorUserAlreadyLogged.toString());

            } else {
                    userSession.setName(login);
                    loginToIsOnline.put(updatedUserSession.getName(), true);
                    userSession.sessionStatus.set(SessionStatus.eAuthorized);
                    ms.sendMessage(new MsgStartGame(this.addressFrontend, ms.getAddressService().getGameMechanics()));
                    response.sendRedirect("/");
            }
        }

        if (request.getPathInfo().equals("/registration")) {

            String login = request.getParameter("login");
            String password = request.getParameter("password");
            String sessionId = request.getSession().getId();
            loggerDB.info("login: " + login + "Pass: " + password);
            sessionIdToRegStatus.put(sessionId, Boolean.FALSE);
            sessionIdToAccountServiceAddress.put(sessionId, ms.getAddressService().getAccountService());
            ms.sendMessage(new MsgRegNewUser(this.addressFrontend, sessionIdToAccountServiceAddress.get(sessionId), login, password, sessionId));

        }

    }

}