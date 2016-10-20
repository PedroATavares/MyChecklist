package pt.isel.ls;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import pt.isel.ls.Commands.*;
import pt.isel.ls.Exceptions.NoSuchCommandException;
import pt.isel.ls.Logic.Arguments;
import pt.isel.ls.Manager.CommandManager;
import java.sql.SQLException;

public class searchCommandTest {

    private static final CommandManager manager= new CommandManager();

    @Before
    public void initialize() throws SQLException {
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

    @Test
    public void test_SearchForCheckListById() throws NoSuchCommandException, SQLException {

        Arguments arg= new Arguments();
        String teste = "POST /checklists/1/tasks name=testecmd1+description=descricaoTeste";
        Command cmd = manager.searchCommand(teste.split(" "),arg);

        Assert.assertTrue(cmd instanceof PostTaskByID);
    }
    @Test
    public void test_SearchForGetCheckListsClosed() throws NoSuchCommandException, SQLException {

        Arguments arg= new Arguments();
        String teste = "GET /checklists/closed";
        Command cmd = manager.searchCommand(teste.split(" "),arg);

        Assert.assertTrue(cmd instanceof GetCheckListsClosed );
    }

    @Test
    public void test_SearchForChangeTaskIsClose() throws NoSuchCommandException, SQLException {

        Arguments arg= new Arguments();
        String teste = "POST /checklists/1/tasks/2 isClosed=true";
        Command cmd = manager.searchCommand(teste.split(" "),arg);

        Assert.assertTrue(cmd instanceof ChangeTaskIsClose );
    }
}
