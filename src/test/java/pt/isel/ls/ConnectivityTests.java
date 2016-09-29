package pt.isel.ls;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import org.junit.Test;

import java.sql.*;
import java.util.Map;
import java.util.Properties;

import static org.junit.Assert.*;

public class ConnectivityTests {
    final String url="jdbc:sqlserver://localhost;";
    private final SQLServerDataSource src = new SQLServerDataSource();
 //   final String user="jdbcuser";
 //   final String password="jdbcuser";

    @Test
    public void connectivity() throws SQLException {
        Connection con;
        con = null;

        Map<String, String> env = System.getenv();

        src.setServerName(env.get("SERVER_NAME"));
        src.setUser(env.get("USER"));
        src.setPassword(env.get("PASSWORD"));
        con=src.getConnection();
        con.setAutoCommit(false);

        PreparedStatement insert1 = con.prepareStatement("insert into student values ('Pedro',5)");
        insert1.executeUpdate();
        PreparedStatement st1 = con.prepareStatement("select * from student where id=5");

        ResultSet rs1 = st1.executeQuery();
        rs1.next();
        assertEquals("Pedro", rs1.getString(1));

        PreparedStatement update = con.prepareStatement("update student set name='Sipriano' where id=5 ");
        update.executeUpdate();

        PreparedStatement st2 = con.prepareStatement("select * from student where id=5");
        ResultSet rs2 = st2.executeQuery();

        rs2.next();
        assertEquals("Sipriano", rs2.getString(1));
        con.rollback();


            /*  ResultSet result = statement.executeQuery();
            while(result.next()){
                System.out.println(result.getString(1) + ' ' + result.getInt(2));
            }
            */
    }
}
