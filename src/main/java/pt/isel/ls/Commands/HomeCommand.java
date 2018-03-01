package pt.isel.ls.Commands;

import pt.isel.ls.Logic.Arguments;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;

public class HomeCommand implements Command {
    @Override
    public Object execute(Arguments args, Connection con) throws SQLException, ParseException {
        
		
        return 0;
    }
    @Override
    public String toString() {
        return "Home - pagina inicial .\n";
    }

}
