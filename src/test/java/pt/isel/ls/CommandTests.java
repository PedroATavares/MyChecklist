package pt.isel.ls;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import pt.isel.ls.Commands.*;
import pt.isel.ls.Logic.Arguments;
import pt.isel.ls.Model.CheckList;
import pt.isel.ls.Model.FullTemplate;
import pt.isel.ls.Model.Template;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.io.*;

public class CommandTests {
    private final SQLServerDataSource src = new SQLServerDataSource();
    private final Map<String, String> env = System.getenv();

    @Before
    public void initialize() throws SQLException {
        String serverName = env.get("SERVER_NAME");
        String pass=env.get("PASSWORD");
        String user=env.get("USER");


        src.setServerName(serverName);
        String database=env.get("DATABASE_NAME");
        if(database!=null)src.setDatabaseName(database);
        src.setUser(user);
        src.setPassword(pass);

       // "sqlcmd -U sa1 -P sa1 -d LSLoc -S DELL -i create.sql"

        StringBuilder arguments=new StringBuilder();
        arguments.append("sqlcmd -U ");
        arguments.append(user);
        arguments.append(" -P ");
        arguments.append(pass);
        if(database!=null){
            arguments.append(" -d ");
            arguments.append(database);
        }
        arguments.append(" -S ");
        arguments.append(serverName);
        arguments.append(" -i create.sql");

        createData(arguments.toString());
    }

    private void createData(String command){
        try {
            String line;
            Process p = Runtime.getRuntime().exec
                    (command);
            BufferedReader input =
                    new BufferedReader
                            (new InputStreamReader(p.getInputStream()));
            while ((line = input.readLine()) != null) {
                System.out.println(line);
            }
            input.close();
        }
        catch (Exception err) {
            err.printStackTrace();
        }
        System.out.println("done");
    }

    @Test
    public void test_CheckListById() throws SQLException {

        Connection  con = src.getConnection();

        GetCheckListByID teste = new GetCheckListByID();
        Arguments arg = new Arguments();

        arg.addVariableParameter("{cid}", "1");

        CheckList result = teste.execute(arg,con);
        Assert.assertTrue(result.id == 1);

        con.close();
    }

    @Test
    public void test_PostTemplate() throws SQLException {
        Connection  con = src.getConnection();
        con.setAutoCommit(false);

        PostTemplate teste = new PostTemplate();
        Arguments arg = new Arguments();
        arg.addArgument("name","Pedro");
        arg.addArgument("description","E o maior");

        int result = teste.execute(arg,con);
        PreparedStatement stm = con.prepareStatement("select * from Template " +
                "where tid = ?" );
        stm.setInt(1, result);
        ResultSet res = stm.executeQuery();
        res.next() ;
        Assert.assertEquals(res.getString(2), "Pedro");

        con.rollback();
        con.close();
    }

    @Test
    public void test_PostCheckLists() throws SQLException {
        Connection  con = src.getConnection();
        con.setAutoCommit(false);

        PostCheckLists teste = new PostCheckLists();
        Arguments arg = new Arguments();

        arg.addArgument("name","Edu");
        arg.addArgument("description","E o quase maior");
        arg.addArgument("dueDate", null);

        int result = teste.execute(arg,con);
        PreparedStatement stm = con.prepareStatement("select * from Checklist " +
                "where cid = ?" );
        stm.setInt(1, result);
        ResultSet res = stm.executeQuery();
        res.next();
        Assert.assertEquals(res.getString(2), "Edu");

        con.rollback();
        con.close();
    }

    @Test
    public void test_GetAllCheckLists() throws SQLException {

        Connection con = src.getConnection();

        GetAllCheckLists teste = new GetAllCheckLists();
        Arguments arg = new Arguments();

        List<CheckList> result = teste.execute(arg, con);
        con.close();

    }

    @Test
    public void test_PostTaskByID() throws SQLException {
        Connection  con = src.getConnection();
        con.setAutoCommit(false);

        PostTaskByID teste = new PostTaskByID();
        Arguments arg = new Arguments();
        Integer input = 1;

        arg.addArgument("name","Goncalo");
        arg.addArgument("description","carrega benfas");
        arg.addArgument("dueDate",null);
        arg.addArgument("isClosed", "false");
        arg.addVariableParameter("{cid}", input.toString());

        int result = teste.execute(arg,con);
        PreparedStatement stm = con.prepareStatement("select * from Task \n" +
                "where lid = ? AND cid = ? " );
        stm.setInt(1, result);
        stm.setInt(2, input );
        ResultSet res = stm.executeQuery();
        res.next();
        Assert.assertEquals( res.getString(3), "Goncalo" );
        Assert.assertEquals(res.getString(1), input.toString() );

        con.rollback();
        con.close();

    }

    @Test
    public void test_ChangeTaskIsClose() throws SQLException {

        Connection con = src.getConnection();
        con.setAutoCommit(false);

        ChangeTaskIsClose teste = new ChangeTaskIsClose();
        Arguments arg = new Arguments();

        Integer input = 1;
        arg.addVariableParameter("{cid}", input.toString());
        arg.addVariableParameter("{lid}", "2");
        arg.addArgument("isClosed", "true");
        String result = teste.execute(arg,con);

        PreparedStatement stm = con.prepareStatement("select * from Task " +
                "where lid = ?");
        stm.setInt(1, input);
        ResultSet rs = stm.executeQuery();
        rs.next();
        Assert.assertEquals(result, rs.getString(6));

        con.setAutoCommit(false);
        con.close();

    }

    @Test
    public void test_PostTemplateTask() throws SQLException {
        Connection  con = src.getConnection();
        con.setAutoCommit(false);

        PostTemplateTask teste = new PostTemplateTask();
        Arguments arg = new Arguments();
        arg.addArgument("name", "Benfica");
        arg.addArgument("description", "E o maior do mundo");
        arg.addVariableParameter("{tid}", "1");

        int result = teste.execute(arg,con);
        PreparedStatement stm = con.prepareStatement("select * from TemplateTask " +
                "where TempTskId = ?" );
        stm.setInt(1, result);
        ResultSet res = stm.executeQuery();
        res.next() ;
        Assert.assertEquals(res.getString(3), "Benfica");

        con.rollback();
        con.close();
    }

    @Test
    public void test_GetTemplates() throws SQLException {

        Connection con = src.getConnection();

        GetTemplates teste = new GetTemplates();
        Arguments arg = new Arguments();

        List<Template> result = teste.execute(arg, con);
        con.close();

    }

    @Test
    public void test_GetTemplateInfoByID() throws SQLException {

        Connection con = src.getConnection();

        GetTemplateInfoByID teste = new GetTemplateInfoByID();
        Arguments arg = new Arguments();

        arg.addVariableParameter("{tid}", "1");
        FullTemplate result = teste.execute(arg, con);
        con.close();

    }

    @Test
    public void test_GetCheckListsClosed() throws SQLException {

        Connection con = src.getConnection();

        GetCheckListsClosed teste = new GetCheckListsClosed();
        Arguments arg = new Arguments();

        List<CheckList> result = teste.execute(arg, con);
        con.close();

    }

    @Test
    public void test_GetCheckListsOpenSortedByDueDate() throws SQLException {

        Connection con = src.getConnection();

        GetCheckListsOpenSortedByDueDate teste = new GetCheckListsOpenSortedByDueDate();
        Arguments arg = new Arguments();

        List<CheckList> result = teste.execute(arg, con);
        con.close();

    }

    @Test
    public void test_GetAllUncompletedChecklistsOrderedByOpenTasks() throws SQLException {
        Connection  con = src.getConnection();
        GetAllUncompletedChecklistsOrderedByOpenTasks teste = new GetAllUncompletedChecklistsOrderedByOpenTasks();
        Arguments arg = new Arguments();

        List<CheckList> result = teste.execute(arg,con);
        for (int i = 0; i < result.size(); i++) {
            System.out.print(result.get(i));
            System.out.println();
        }
        con.close();
    }
}
