package pt.isel.ls;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import pt.isel.ls.Commands.*;
import pt.isel.ls.Exceptions.NoSuchCommandException;
import pt.isel.ls.Logic.Arguments;
import pt.isel.ls.Manager.CommandManager;
import pt.isel.ls.Model.CheckList;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class App {
    private static final SQLServerDataSource src = new SQLServerDataSource();
    private static final Map<String, String> env = System.getenv();
    private static final CommandManager manager= new CommandManager();

    public  static void  main(String [] args) throws SQLException, NoSuchCommandException {
        initialize();
        Arguments arg= new Arguments();
        Command cmd=manager.searchCommand(args, arg);
        System.out.println(cmd.execute(arg));

        System.out.print("-----A BOMBAR!!-----");
    }

    private static void initialize() throws SQLServerException {
        src.setServerName(env.get("SERVER_NAME"));
        String curr=env.get("DATABASE_NAME");
        if(curr!=null)  src.setDatabaseName(curr);
        src.setUser(env.get("USER"));
        src.setPassword(env.get("PASSWORD"));




        manager.addCommand("POST /checklists/{cid}/tasks/{lid}", new ChangeTaskIsClose(src.getConnection()));
        manager.addCommand("GET /checklists", new GetAllCheckLists(src.getConnection()));
        manager.addCommand("GET /checklists/{cid}", new GetCheckListByID(src.getConnection()));
        manager.addCommand("GET /templates", new GetTemplates(src.getConnection()));
        manager.addCommand("POST /checklists", new PostCheckLists(src.getConnection()));
        manager.addCommand("POST /checklists/{cid}/tasks", new PostTaskByID(src.getConnection()));
        manager.addCommand("POST /templates", new PostTemplate(src.getConnection()));
        manager.addCommand("POST /templates/{tid}/tasks", new PostTemplateTask(src.getConnection()));
    }
}
