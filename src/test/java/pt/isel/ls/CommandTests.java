package pt.isel.ls;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import pt.isel.ls.Commands.*;
import pt.isel.ls.Logic.Arguments;
import pt.isel.ls.Model.CheckList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class CommandTests {
    private final SQLServerDataSource src = new SQLServerDataSource();
    private final Map<String, String> env = System.getenv();

    @Before
    public void initialize() throws SQLException {
        src.setServerName(env.get("SERVER_NAME"));
        String curr=env.get("DATABASE_NAME");
        if(curr!=null)src.setDatabaseName(curr);
        src.setUser(env.get("USER"));
        src.setPassword(env.get("PASSWORD"));
    }

    @Test
    public void test_CheckListById() throws SQLException {

        Connection  con = src.getConnection();
        GetCheckListByID teste = new GetCheckListByID(con);
        Arguments arg = new Arguments();

        arg.addVariableParameter("{cid}", "1");

        CheckList result = teste.execute(arg);
        Assert.assertTrue(result.id == 1);
        con.close();
    }

    @Test
    public void test_PostTemplate() throws SQLException {
        Connection  con = src.getConnection();
        PostTemplate teste = new PostTemplate(con);
        Arguments arg = new Arguments();
        arg.addArgument("name","Pedro");
        arg.addArgument("descrip","E o maior");

        int result = teste.execute(arg);
        PreparedStatement stm = con.prepareStatement("select * from Template " +
                "where tid = ?" );

        stm.setInt(1, result);
        ResultSet res = stm.executeQuery();
        res.next() ;
        Assert.assertEquals( res.getString(2), "Pedro" );
    }

    @Test
    public void test_PostCheckLists() throws SQLException {
        Connection  con = src.getConnection();
        PostCheckLists teste = new PostCheckLists(con);
        Arguments arg = new Arguments();

        arg.addArgument("name","Edu");
        arg.addArgument("descrip","E o quase maior");
        arg.addArgument("dueDate", null);

        int result = teste.execute(arg);
        System.out.print("PostCheckLists-------");
    }

    @Test
    public void test_GetAllCheckLists() throws SQLException {

        Connection  con = src.getConnection();
        GetAllCheckLists teste = new GetAllCheckLists(con);
        Arguments arg = new Arguments();

        List<CheckList> result = teste.execute(arg);
        System.out.print("AllCheckLists-----");

        //----------- PRECISAMOS DE ARRANJAR MANEIRA DE TESTAR A CENA PA PASSAR NOS TESTES
    }

    @Test
    public void test_PostTaskByID() throws SQLException {
        Connection  con = src.getConnection();
        PostTaskByID teste = new PostTaskByID(con);
        Arguments arg = new Arguments();

        arg.addArgument("name","Goncalo");
        arg.addArgument("descrip","carrega benfas");
        arg.addArgument("dueDate",null);
        arg.addVariableParameter("{cid}", "4");

        int result = teste.execute(arg);
        System.out.print("Post Tast By CID-------");
    }

}
