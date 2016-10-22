package pt.isel.ls.Commands;

import pt.isel.ls.Logic.Arguments;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Map;

public interface Command {

    public Object execute(Arguments args, Connection con) throws SQLException, ParseException;
}
