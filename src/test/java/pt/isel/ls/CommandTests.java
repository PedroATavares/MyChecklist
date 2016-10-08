package pt.isel.ls;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import pt.isel.ls.Commands.*;
import pt.isel.ls.Logic.Arguments;
import pt.isel.ls.Model.CheckList;
import pt.isel.ls.Model.Template;

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
        con.close();
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
        PreparedStatement stm = con.prepareStatement("select * from Checklist " +
                "where cid = ?" );
        stm.setInt(1, result);
        ResultSet res = stm.executeQuery();
        res.next();
        Assert.assertEquals( res.getString(2), "Edu");
        con.close();
    }

    @Test
    public void test_GetAllCheckLists() throws SQLException {

        Connection  con = src.getConnection();
        GetAllCheckLists teste = new GetAllCheckLists(con);
        Arguments arg = new Arguments();

        List<CheckList> result = teste.execute(arg);
        System.out.print("AllCheckLists-----");
        con.close();

        //----------- PRECISAMOS DE ARRANJAR MANEIRA DE TESTAR A CENA PA PASSAR NOS TESTES porque se
        // fizer normal vai ser igual ao metodo em si e é só parvo !!
    } // ----- VER

    @Test
    public void test_PostTaskByID() throws SQLException {
        Connection  con = src.getConnection();
        PostTaskByID teste = new PostTaskByID(con);
        Arguments arg = new Arguments();

        Integer input = 4; // para dar pó teste

        arg.addArgument("name","Goncalo");
        arg.addArgument("descrip","carrega benfas");
        arg.addArgument("dueDate",null);
        arg.addVariableParameter("{cid}", input.toString()); // ver esta parte do toString

        int result = teste.execute(arg);
        PreparedStatement stm = con.prepareStatement("select * from Task \n" +
                "where lid = ? AND cid = ? " );
        stm.setInt(1, result);
        stm.setInt(2, input ); // o que recebe nos variableParameters
        ResultSet res = stm.executeQuery();
        res.next();
        Assert.assertEquals( res.getString(3), "Goncalo" );
        Assert.assertEquals( res.getString(1), input.toString() ); // porque aqui tambem preciso do gajo
        con.close();

    } //  BOMBA mas é preciso ver ainda a parte do CID (input)

    @Test
    public void test_ChangeTaskIsClose() throws SQLException {

        Connection  con = src.getConnection();
        ChangeTaskIsClose teste = new ChangeTaskIsClose(con);
        Arguments arg = new Arguments();

        Integer input = 4;
        arg.addVariableParameter("{cid}", input.toString());
        arg.addVariableParameter("{lid}", "6");
        arg.addArgument("isClosed", "true");

        String result = teste.execute(arg);

        PreparedStatement stm = con.prepareStatement("select * from Task " +
                "where lid = ?");

        stm.setInt( 1, input );
        ResultSet rs = stm.executeQuery();
        rs.next();

        Assert.assertEquals(result, rs.getString(6));
        con.close();

    }

    @Test
    public void test_PostTemplateTask() throws SQLException {
        Connection  con = src.getConnection();
        PostTemplateTask teste = new PostTemplateTask(con);
        Arguments arg = new Arguments();
        arg.addArgument("name","Benfica");
        arg.addArgument("descrip","E o maior do mundo");
        arg.addVariableParameter("{tid}", "8");

        int result = teste.execute(arg);
        PreparedStatement stm = con.prepareStatement("select * from TemplateTask " +
                "where TempTskId = ?" );
        stm.setInt(1, result);
        ResultSet res = stm.executeQuery();
        res.next() ;
        Assert.assertEquals( res.getString(3), "Benfica" );
        con.close();
    }

    @Test
    public void test_GetTemplates() throws SQLException {

        Connection  con = src.getConnection();
        GetTemplates teste = new GetTemplates(con);
        Arguments arg = new Arguments();

        List<Template> result = teste.execute(arg);
        System.out.print("All Templates-----");
        con.close();

        //----------- VER DOS TESTES
    } // ----- VER


}