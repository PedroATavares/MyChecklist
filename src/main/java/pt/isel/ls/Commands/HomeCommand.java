package pt.isel.ls.Commands;

import pt.isel.ls.Logic.Arguments;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;

public class HomeCommand implements Command {
    @Override
    public Object execute(Arguments args, Connection con) throws SQLException, ParseException {
        return null;
    }
}
