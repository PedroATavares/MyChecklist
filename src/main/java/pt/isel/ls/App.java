package pt.isel.ls;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import pt.isel.ls.Commands.*;
import pt.isel.ls.Exceptions.NoSuchCommandException;
import pt.isel.ls.Logic.Arguments;
import pt.isel.ls.Manager.CommandManager;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Map;

public class App {
    private static final SQLServerDataSource src = new SQLServerDataSource();
    private static final Map<String, String> env = System.getenv();
    private static final CommandManager manager= new CommandManager();

    public  static void  main(String [] args) throws SQLServerException {
        if(args.length<2){
            System.out.println("Please Provide a method and a path");
            return;
        }
        initialize();
        try (Connection con = src.getConnection()){
            Arguments cmmandArguments= new Arguments();
            Command cmd= manager.searchCommand(args, cmmandArguments);
            if(args.length>=3)
                manager.fillArguments(args[2], cmmandArguments);

            System.out.println(cmd.execute(cmmandArguments, con));
        }catch (SQLException e) {
            System.out.println(e.getMessage());

        } catch (NoSuchCommandException e) {
            System.out.println(e.getMessage());
        } catch (ParseException e) {
            System.out.println(e.getMessage());
        }
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
        manager.addCommand("POST /tags", new PostTags());
        manager.addCommand("GET /tags", new GetAllTags());
        manager.addCommand("DELETE /tags/{gid}", new DeleteTagsByID());
        manager.addCommand("DELETE /checklists/{cid}/tags/{gid}", new DeleteCheckListWithCidBygID());

    }
}
