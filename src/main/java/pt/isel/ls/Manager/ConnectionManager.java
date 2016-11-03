package pt.isel.ls.Manager;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import com.microsoft.sqlserver.jdbc.SQLServerException;

import java.sql.Connection;
import java.util.Map;

public class ConnectionManager {
    private final Map<String, String> env = System.getenv();
    private final SQLServerDataSource src = new SQLServerDataSource();

    public ConnectionManager() {
        src.setServerName(env.get("SERVER_NAME"));
        String curr=env.get("DATABASE_NAME");
        if(curr!=null)  src.setDatabaseName(curr);
        src.setUser(env.get("USER"));
        src.setPassword(env.get("PASSWORD"));
    }

    public Connection getConection(){
        try {
            return src.getConnection();
        } catch (SQLServerException e) {
            System.out.print("Cannot get connection at: " + src.getServerName() + "DB name: " + src.getDatabaseName());
            return null;
        }
    }
}
