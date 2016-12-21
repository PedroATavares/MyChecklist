package pt.isel.ls.Commands;

import pt.isel.ls.Logic.Arguments;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;

public class Exit implements Command {

    public Exit(){
    }

    @Override
    public String execute(Arguments args, Connection con) throws SQLException, ParseException {
        System.out.println("Exit done ");

        return "EXIT";
    }

    @Override
    public String toString() {
        return "EXIT / - ends the application.";
    }
}
