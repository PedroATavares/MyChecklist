package pt.isel.ls;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import org.junit.Test;

import java.sql.*;
import java.util.Map;
import java.util.Properties;

import static org.junit.Assert.*;

public class ConnectivityTests {
    private final SQLServerDataSource src = new SQLServerDataSource();

    @Test(expected=SQLServerException.class)
    public void teste_error_connect()  throws SQLServerException{
        Connection con = null;

            Map<String, String> env = System.getenv();

            src.setServerName(env.get("ausdjadaks"));
            src.setUser(env.get("ssa"));
            src.setPassword(env.get("hfgdf"));
            con = src.getConnection();
    }

    @Test
    public void connectivity() throws SQLException {
        Connection con;
        con = null;

        Map<String, String> env = System.getenv();

        src.setServerName(env.get("SERVER_NAME"));
        String curr=env.get("DATABASE_NAME");
        if(curr!=null)src.setDatabaseName(curr);
        src.setUser(env.get("USER"));
        src.setPassword(env.get("PASSWORD"));
        con=src.getConnection();

        con.setAutoCommit(false);

        PreparedStatement confirm1 = con.prepareStatement("IF object_id('studentTest', 'U') is not null\n" +
                "        begin\n" +
                "        drop table studentTest\n" +
                "        end");
        confirm1.execute();

        PreparedStatement create1 = con.prepareStatement( "create table studentTest(\n" +
                "\tname varchar(10) ,\n" +
                "\tid int primary Key\n" +
                " )");
        create1.execute();

        PreparedStatement insert1 = con.prepareStatement("insert into studentTest values ('Pedro',5)");
        insert1.executeUpdate();
        PreparedStatement st1 = con.prepareStatement("select * from studentTest where id=5");

        ResultSet rs1 = st1.executeQuery();
        rs1.next();
        assertEquals("Pedro", rs1.getString(1));

        PreparedStatement update = con.prepareStatement("update studentTest set name='Sipriano' where id=5 ");
        update.executeUpdate();

        PreparedStatement st2 = con.prepareStatement("select * from studentTest where id=5");
        ResultSet rs2 = st2.executeQuery();

        rs2.next();
        assertEquals("Sipriano", rs2.getString(1));
        con.rollback();
    }
}
