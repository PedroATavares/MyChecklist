package pt.isel.ls.Commands;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import pt.isel.ls.Logic.Arguments;
import pt.isel.ls.Manager.CommandManager;
import pt.isel.ls.Server.HttpServer;

import java.sql.Connection;


public class ListenCommand implements Command {

    private final CommandManager manager;


    public ListenCommand(CommandManager manager) {
        this.manager = manager;
    }

    @Override
    public Object execute(Arguments args, Connection con) {
        Server server = new Server(Integer.parseInt(args.arguments.get("port")));
        ServletHandler handler = new ServletHandler();
        server.setHandler(handler);
        handler.addServletWithMapping(new ServletHolder(new HttpServer(manager)), "/*");
        try {
            server.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
}
