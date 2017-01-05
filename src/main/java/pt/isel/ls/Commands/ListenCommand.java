package pt.isel.ls.Commands;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pt.isel.ls.Logic.Arguments;
import pt.isel.ls.Manager.CommandManager;
import pt.isel.ls.Server.HttpServer;

import java.sql.Connection;


public class ListenCommand implements Command {

    private final CommandManager manager;
    private String defaultPort="8080";
    private Logger logger;

    public ListenCommand(CommandManager manager) {
        this.manager = manager;
        logger = LoggerFactory.getLogger(HttpServer.class);
    }

    @Override
    public Object execute(Arguments args, Connection con) {
        String port =args.arguments.get("port");
        if(port==null) port=System.getenv("PORT");
        if(port==null) port=defaultPort;
        Server server = new Server(Integer.parseInt(port));
        ServletHandler handler = new ServletHandler();
        server.setHandler(handler);
        handler.addServletWithMapping(new ServletHolder(new HttpServer(manager)), "/*");
        try {
            logger.info("The server started in the port: " + defaultPort );
            server.start();
        } catch (Exception e) {
            logger.info("The error: " + e.getMessage() + " has been reached, so the server was shutdown");
            e.printStackTrace();
        }
        return 0;
    }
}
