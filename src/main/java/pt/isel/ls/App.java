package pt.isel.ls;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import pt.isel.ls.Commands.*;
import pt.isel.ls.Exceptions.NoSuchCommandException;
import pt.isel.ls.Html.Parcers.CheckListParcer;
import pt.isel.ls.Html.Parcers.FullTemplateParcer;
import pt.isel.ls.Html.Parcers.TagParcer;
import pt.isel.ls.Html.Parcers.TemplateParcer;
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

        manager.addCommand("POST /templates/{tid}/create",new PostTemplateInstance());
        manager.addCommand("POST /checklists/{cid}/tasks/{lid}", new ChangeTaskIsClose() );
        manager.addCommand("GET /checklists", new GetCommand( new GetAllCheckLists(),
                null,
                new CheckListParcer()
        ));

        manager.addCommand("GET /checklists/{cid}", new GetCommand( new GetCheckListByID(),
                null,
                new CheckListParcer()
        ));
        manager.addCommand("GET /templates", new GetCommand( new GetTemplates(),
                null,
                new TemplateParcer()
        ));
        manager.addCommand("POST /checklists", new PostCheckLists() );
        manager.addCommand("POST /checklists/{cid}/tasks", new PostTaskByID() );
        manager.addCommand("POST /templates", new PostTemplate() );
        manager.addCommand("POST /templates/{tid}/tasks", new PostTemplateTask());
        manager.addCommand("GET /templates/{tid}", new GetCommand( new GetTemplateInfoByID(),
                null,
                new FullTemplateParcer()
        ));
        manager.addCommand("GET /checklists/closed", new GetCommand( new GetCheckListsClosed(),
                null,
                new CheckListParcer()
        ));
        manager.addCommand("GET /checklists/open/sorted/duedate", new GetCommand( new GetCheckListsOpenSortedByDueDate(),
                null,
                new CheckListParcer()
        ));
        manager.addCommand("GET /checklists/open/sorted/noftasks", new GetCommand( new GetAllUncompletedChecklistsOrderedByOpenTasks(),
                null,
                new CheckListParcer()
        ));
        manager.addCommand("POST /checklists/{cid}/tags", new PostTagInCheckListByID());
        manager.addCommand("EXIT /", new Exit() );
        manager.addCommand("POST /tags", new PostTags());
        manager.addCommand("GET /tags", new GetCommand( new GetAllTags(),
                null,
                new TagParcer()
        ));
        manager.addCommand("DELETE /tags/{gid}", new DeleteTagsByID());
        manager.addCommand("DELETE /checklists/{cid}/tags/{gid}", new DeleteCheckListWithCidBygID());
    }
}
