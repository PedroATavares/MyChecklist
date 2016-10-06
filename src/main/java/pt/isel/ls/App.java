package pt.isel.ls;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import pt.isel.ls.Commands.Command;
import pt.isel.ls.Commands.GetAllCheckLists;
import pt.isel.ls.Model.CheckList;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * Created by HP on 04/10/2016.
 */
public class App {
    private static final SQLServerDataSource src = new SQLServerDataSource();
    private static final Map<String, String> env = System.getenv();

    public  static void  main(String [] args) throws SQLException {
    }
}
