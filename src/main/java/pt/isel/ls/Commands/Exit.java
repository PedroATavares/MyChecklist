package pt.isel.ls.Commands;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pt.isel.ls.Logic.Arguments;
import pt.isel.ls.Server.HttpServer;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Exit implements Command {
    private Logger logger;
    private DateFormat dateFormat;
    private Date date;

    public Exit(){
        logger = LoggerFactory.getLogger(HttpServer.class);
        dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        date = new Date();
    }

    @Override
    public String execute(Arguments args, Connection con) throws SQLException, ParseException {
        logger.info( dateFormat.format(date) + " | Exit has been done ");
        return "EXIT";
    }

    @Override
    public String toString() {
        return "EXIT / - ends the application.";
    }
}
