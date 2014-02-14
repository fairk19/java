package main;

import admin.AdminPageServlet;
import accountService.AccountServiceImpl;
import base.AccountService;
import base.Frontend;
import base.GameSocketServlet;
import base.VFS;
import frontend.FrontendImpl;
import gameMechanics.GameMechanicsImpl;
import gameMechanics.GameSocketServletImpl;
import messageSystem.MessageSystemImpl;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import resourceSystem.sax.ReadXMLFileSAX;
import utils.LoggerFactory;
import vfs.VFSImpl;

import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created with IntelliJ IDEA.
 * User: alexandr
 * Date: 27.09.13
 * Time: 22:37
 * To change this template use File | Settings | File Templates.
 */
public class Main {


    private static Logger serverLogger = LoggerFactory.getLogger("ServerLogger", "./log/server-log.txt");

    public static void main(String[] args) throws Exception {

        if (args.length != 1) {
            serverLogger.log(Level.WARNING, "Use port as the first argument");
            System.exit(1);
        }

        String portString = args[0];
        int port = Integer.valueOf(portString);
        serverLogger.info("Запуск на порте: " + portString + "\n");

        loadResourсe();

        MessageSystemImpl ms = new MessageSystemImpl();
        Frontend frontend = new FrontendImpl(ms);
        AccountService accountService = new AccountServiceImpl(ms);
        GameMechanicsImpl gameMechanics = new GameMechanicsImpl(ms);
        GameSocketServlet gameSocketServlet = new GameSocketServletImpl(ms);

        (new Thread(frontend)).start();
        (new Thread(accountService)).start();
        (new Thread(gameMechanics)).start();
        (new Thread(gameSocketServlet)).start();

        Server server = new Server();
        ServerConnector connector = new ServerConnector(server);
        connector.setPort(port);
        server.addConnector(connector);

        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.addServlet(new ServletHolder(new AdminPageServlet()), AdminPageServlet.adminPageURL);
        context.addServlet(new ServletHolder((FrontendImpl)frontend), Frontend.homePageURL);
        context.addServlet(new ServletHolder(GameSocketServletImpl.class), Frontend.playPageURL);

        ResourceHandler resource_handler = new ResourceHandler();
        resource_handler.setDirectoriesListed(true);
        resource_handler.setResourceBase("static");

        HandlerList handlers = new HandlerList();
        handlers.setHandlers(new Handler[]{resource_handler, context});
        server.setHandler(handlers);

        server.start();
        server.join();

    }

    private static void loadResourсe() {

        VFS vfs= new VFSImpl("");
        Iterator<String> iter = vfs.getIterator("./src/main/resources");
        iter.next();

        while(iter.hasNext()){
            ReadXMLFileSAX.readXML(iter.next());
        }
    }
}
