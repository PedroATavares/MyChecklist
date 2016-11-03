package pt.isel.ls;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import pt.isel.ls.Commands.*;
import pt.isel.ls.Exceptions.NoSuchCommandException;
import pt.isel.ls.Html.Parcers.CheckListParcer;
import pt.isel.ls.Logic.Arguments;
import pt.isel.ls.Manager.CommandManager;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Map;

public class App {
    private static final CommandManager manager= new CommandManager();

    public  static void  main(String [] args) throws SQLServerException {
        if(args.length<2){
            System.out.println("Please Provide a method and a path");
            return;
        }
        initialize();

            manager.searchAndExecute(args);

    }

    private static void initialize() throws SQLServerException {

        manager.addCommand("POST /templates/{tid}/create", new PostTemplateInstance());
        manager.addCommand("POST /checklists/{cid}/tasks/{lid}", new ChangeTaskIsClose());
        manager.addCommand("GET /checklists", new GetAllCheckLists());

        manager.addCommand("GET /checklists/{cid}", new GetCommand( new GetCheckListByID(),
                null,
                new CheckListParcer()
        ));

        manager.addCommand("GET /templates", new GetTemplates());
        manager.addCommand("POST /checklists", new PostCheckLists());
        manager.addCommand("POST /checklists/{cid}/tasks", new PostTaskByID());
        manager.addCommand("POST /templates", new PostTemplate());
        manager.addCommand("POST /templates/{tid}/tasks", new PostTemplateTask());
        manager.addCommand("GET /templates/{tid}", new GetTemplateInfoByID());
        manager.addCommand("GET /checklists/closed", new GetCheckListsClosed());
        manager.addCommand("GET /checklists/open/sorted/duedate", new GetCheckListsOpenSortedByDueDate());
        manager.addCommand("GET /checklists/open/sorted/noftasks", new GetAllUncompletedChecklistsOrderedByOpenTasks());
<<<<<<< HEAD
        manager.addCommand("POST /checklists/{cid}/tags", new PostTagInCheckListByID() );
        manager.addCommand("EXIT /", new Exit() );
=======
        manager.addCommand("POST /tags", new PostTags());
        manager.addCommand("GET /tags", new GetAllTags());
        manager.addCommand("DELETE /tags/{gid}", new DeleteTagsByID());
        manager.addCommand("DELETE /checklists/{cid}/tags/{gid}", new DeleteCheckListWithCidBygID());

>>>>>>> b4b5fe8decf8c8672713b1336dca4b21838bc805
    }
}
