package pt.isel.ls.Commands;

import pt.isel.ls.Logic.Arguments;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;

public interface Command<E> {

    public E execute(Arguments args,Connection con) throws SQLException;
}
