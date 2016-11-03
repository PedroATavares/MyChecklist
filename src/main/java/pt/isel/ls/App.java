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

        manager.addCommand("POST /templates/{tid}/create",  new GetCommand( new PostTemplateInstance(),
                null,
                null
        ));
        manager.addCommand("POST /checklists/{cid}/tasks/{lid}", new GetCommand( new ChangeTaskIsClose(),
                null,
                null
        ));
        manager.addCommand("GET /checklists", new GetCommand( new GetAllCheckLists(),
                null,
                null
        ));

        manager.addCommand("GET /checklists/{cid}", new GetCommand( new GetCheckListByID(),
                null,
                new CheckListParcer()
        ));
        manager.addCommand("GET /templates", new GetCommand( new GetTemplates(),
                null,
                null
        ));
        manager.addCommand("POST /checklists",  new GetCommand( new PostCheckLists(),
                null,
                null
        ));
        manager.addCommand("POST /checklists/{cid}/tasks", new GetCommand( new PostTaskByID(),
                null,
                null
        ));
        manager.addCommand("POST /templates", new GetCommand( new PostTemplate(),
                null,
                null
        ));
        manager.addCommand("POST /templates/{tid}/tasks", new GetCommand( new PostTemplateTask(),
                null,
                null
        ));
        manager.addCommand("GET /templates/{tid}", new GetCommand( new GetTemplateInfoByID(),
                null,
                null
        ));
        manager.addCommand("GET /checklists/closed", new GetCommand( new GetCheckListsClosed(),
                null,
                null
        ));
        manager.addCommand("GET /checklists/open/sorted/duedate", new GetCommand( new GetCheckListsOpenSortedByDueDate(),
                null,
                null
        ));
        manager.addCommand("GET /checklists/open/sorted/noftasks", new GetCommand( new GetAllUncompletedChecklistsOrderedByOpenTasks(),
                null,
                null
        ));
        manager.addCommand("POST /checklists/{cid}/tags", new GetCommand( new PostTagInCheckListByID(),
                null,
                null
        ));
        manager.addCommand("EXIT /", new Exit() );
        manager.addCommand("POST /tags", new GetCommand( new PostTags(),
                null,
                null
        ));
        manager.addCommand("GET /tags", new GetCommand( new GetAllTags(),
                null,
                null
        ));
        manager.addCommand("DELETE /tags/{gid}", new GetCommand( new DeleteTagsByID(),
                null,
                null
        ));
        manager.addCommand("DELETE /checklists/{cid}/tags/{gid}", new GetCommand( new DeleteCheckListWithCidBygID(),
                null,
                null
        ));
    }
}
