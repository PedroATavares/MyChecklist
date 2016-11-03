package pt.isel.ls.Commands;

import pt.isel.ls.Logic.Arguments;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;

public class Exit implements Command {

    public Exit(){
    }

    @Override
    public Integer execute(Arguments args, Connection con) throws SQLException, ParseException {
        System.out.println("Exit done ");
        System.exit(0);
        return 0;
    }
}
