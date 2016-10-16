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
        Command cmd= manager.searchCommand(args, arg);
        Connection con = src.getConnection();
        System.out.println(cmd.execute(arg,con));
        con.close();
    }

    private static void initialize() throws SQLServerException {
        src.setServerName(env.get("SERVER_NAME"));
        String curr=env.get("DATABASE_NAME");
        if(curr!=null)  src.setDatabaseName(curr);
        src.setUser(env.get("USER"));
        src.setPassword(env.get("PASSWORD"));

        manager.addCommand("POST /templates/{tid}/create", new PostTemplateInstance());
        manager.addCommand("POST /checklists/{cid}/tasks/{lid}", new ChangeTaskIsClose());
        manager.addCommand("GET /checklists", new GetAllCheckLists());
        manager.addCommand("GET /checklists/{cid}", new GetCheckListByID());
        manager.addCommand("GET /templates", new GetTemplates());
        manager.addCommand("POST /checklists", new PostCheckLists());
        manager.addCommand("POST /checklists/{cid}/tasks", new PostTaskByID());
        manager.addCommand("POST /templates", new PostTemplate());
        manager.addCommand("POST /templates/{tid}/tasks", new PostTemplateTask());
        manager.addCommand("GET /templates/{tid}", new GetTemplateInfoByID());
        manager.addCommand("GET /checklists/closed", new GetCheckListsClosed());
        manager.addCommand("GET /checklists/open/sorted/duedate", new GetCheckListsOpenSortedByDueDate());
        manager.addCommand("GET /checklists/open/sorted/noftasks", new GetAllUncompletedChecklistsOrderedByOpenTasks());


    }
}
