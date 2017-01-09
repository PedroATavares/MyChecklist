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

    @Override
    public String execute(Arguments args, Connection con) throws SQLException, ParseException {
        return "EXIT";
    }

    @Override
    public String toString() {
        return "EXIT / - ends the application.";
    }
}
