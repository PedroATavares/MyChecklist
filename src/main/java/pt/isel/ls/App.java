package pt.isel.ls;

import com.microsoft.sqlserver.jdbc.SQLServerException;
import pt.isel.ls.Commands.*;
import pt.isel.ls.Html.Parcers.*;
import pt.isel.ls.Json.Parcers.*;
import pt.isel.ls.Manager.CommandManager;

import java.util.Scanner;

public class App {
    private static final CommandManager manager= new CommandManager();

    public  static void  main(String [] args) throws SQLServerException {

        initialize();

        if (args.length>=1)
            manager.searchAndExecute(args);
        else{
            Scanner sc = new Scanner(System.in);
            while(true){
                System.out.print('>');
                String input =sc.nextLine();
                manager.searchAndExecute(input.split(" "));
            }
        }
    }

    private static void initialize() throws SQLServerException {

        manager.addCommand("POST /templates/{tid}/create",new PostTemplateInstance());
        manager.addCommand("POST /checklists/{cid}/tasks/{lid}", new ChangeTaskIsClose() );
        manager.addCommand("GET /checklists", new GetCommand( new GetAllCheckLists(),
                new ChecklistListJsonFormat(),
                new ChecklistListParser()
        ));

        manager.addCommand("GET /checklists/{cid}", new GetCommand( new GetCheckListByID(),
                new CheckListJsonFormat(),
                new ChecklistParser()
        ));
        manager.addCommand("GET /templates", new GetCommand( new GetTemplates(),
                new TemplateListJsonFormat(),
                new TemplateParser()
        ));
        manager.addCommand("POST /checklists", new PostCheckLists() );
        manager.addCommand("POST /checklists/{cid}/tasks", new PostTaskByID() );
        manager.addCommand("POST /templates", new PostTemplate() );
        manager.addCommand("POST /templates/{tid}/tasks", new PostTemplateTask());
        manager.addCommand("GET /templates/{tid}", new GetCommand( new GetTemplateInfoByID(),
                new FullTemplateJsonFormat(),
                new FullTemplateParser()
        ));
        manager.addCommand("GET /checklists/closed", new GetCommand( new GetCheckListsClosed(),
                new ChecklistListJsonFormat(),
                new ChecklistListParser()
        ));
        manager.addCommand("GET /checklists/open/sorted/duedate", new GetCommand( new GetCheckListsOpenSortedByDueDate(),
                new ChecklistListJsonFormat(),
                new ChecklistListParser()
        ));
        manager.addCommand("GET /checklists/open/sorted/noftasks", new GetCommand( new GetAllUncompletedChecklistsOrderedByOpenTasks(),
                new ChecklistListJsonFormat(),
                new ChecklistListParser()
        ));
        manager.addCommand("POST /checklists/{cid}/tags", new PostTagInCheckListByID());
        manager.addCommand("EXIT /", new Exit() );
        manager.addCommand("POST /tags", new PostTags());
        manager.addCommand("GET /tags", new GetCommand( new GetAllTags(),
                new TagListJsonFormat(),
                new TagListParser()
        ));
        manager.addCommand("DELETE /tags/{gid}", new DeleteTagsByID());
        manager.addCommand("DELETE /checklists/{cid}/tags/{gid}", new DeleteCheckListWithCidBygID());
        manager.addCommand("OPTIONS /", new OptionCommand(manager));
        manager.addCommand("LISTEN /", new ListenCommand(manager));



    }


}
