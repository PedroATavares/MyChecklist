package pt.isel.ls.Commands;

import pt.isel.ls.Logic.Arguments;

import java.sql.SQLException;
import java.util.Map;

/**
 * Created by HP on 04/10/2016.
 */
public interface Command<E> {

    public E execute(Arguments args) throws SQLException;
}
