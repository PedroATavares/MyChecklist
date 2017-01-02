package pt.isel.ls.Commands;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pt.isel.ls.Logic.Arguments;
import pt.isel.ls.Server.HttpServer;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;

public class Exit implements Command {
    private Logger logger;

    public Exit(){
        logger = LoggerFactory.getLogger(HttpServer.class);
    }

    @Override
    public String execute(Arguments args, Connection con) throws SQLException, ParseException {
        logger.info("Exit done ");

        return "EXIT";
    }

    @Override
    public String toString() {
        return "EXIT / - ends the application.";
    }
}
