package pt.isel.ls;

import com.microsoft.sqlserver.jdbc.SQLServerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pt.isel.ls.Commands.*;
import pt.isel.ls.Exceptions.NoSuchCommandException;
import pt.isel.ls.Exceptions.NoSuchElementException;
import pt.isel.ls.Html.Parcers.*;
import pt.isel.ls.Json.Parcers.*;
import pt.isel.ls.Manager.CommandManager;
import pt.isel.ls.Server.HttpServer;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.Scanner;

public class App {
    private static final CommandManager manager= new CommandManager();
    private static Logger logger = LoggerFactory.getLogger(HttpServer.class);

    public static void  main(String [] args) throws SQLServerException {

        initialize();
        if (args.length >= 1)
            try {
                manager.searchAndExecute(args);
            } catch (NoSuchCommandException e) {
                logger.info(e.getMessage());
            } catch (ParseException e) {
                logger.info(e.getMessage());
            } catch (SQLException e) {
                logger.info(e.getMessage());
            }catch (NumberFormatException e){
                logger.info(e.getMessage());
            } catch (NoSuchElementException e) {
               logger.info(e.getMessage());
            }

        else {

            String result=null;
            Scanner sc = new Scanner(System.in);

            while (true) {
                logger.info(">");
                String input = sc.nextLine();
                try {
                    result=manager.searchAndExecute(input.split(" "));
                    if(result.equals("EXIT"))break;
                    manager.handlePrint(result);
                } catch (NoSuchCommandException e) {
                    logger.info(e.getMessage());
                } catch (ParseException e) {
                    logger.info(e.getMessage());
                } catch (SQLException e) {
                    logger.info(e.getMessage());
                }catch (NumberFormatException e){
                    logger.info(e.getMessage());
                }catch (NullPointerException e){
                } catch (NoSuchElementException e) {
                    logger.info(e.getMessage());
                }
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
                new CheckListClosedParser()
        ));
        manager.addCommand("GET /checklists/open/sorted/duedate", new GetCommand( new GetCheckListsOpenSortedByDueDate(),
                new ChecklistListJsonFormat(),
                new CheckListSortedDuedateParser()
        ));
        manager.addCommand("GET /checklists/open/sorted/noftasks", new GetCommand( new GetAllUncompletedChecklistsOrderedByOpenTasks(),
                new ChecklistListJsonFormat(),
                new CheckListSortedNofTasksParser()
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
        manager.addCommand("GET /tags/{gid}/checklists",new GetCommand(new GetChecklistsByTagID(),
                new ChecklistListJsonFormat(),
                new CheckListByGidParser()
        ));
        manager.addCommand("GET /tags/{gid}", new GetCommand( new GetTagsByID(),
                new TagJsonFormat(),
                new TagParser()
        ));
        manager.addCommand("GET /", new GetCommand(new HomeCommand(),null,new HomePageParser()));

    }


}
