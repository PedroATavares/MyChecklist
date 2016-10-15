package pt.isel.ls.Manager;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;

import java.util.Map;

public class ConnectionManager {
    private final SQLServerDataSource src = new SQLServerDataSource();
    private final Map<String, String> env = System.getenv();

    public ConnectionManager() {
        src.setServerName(env.get("SERVER_NAME"));
        String curr=env.get("DATABASE_NAME");
        if(curr!=null)  src.setDatabaseName(curr);
        src.setUser(env.get("USER"));
        src.setPassword(env.get("PASSWORD"));
    }

    public SQLServerDataSource getSrc() {
        return src;
    }
}
