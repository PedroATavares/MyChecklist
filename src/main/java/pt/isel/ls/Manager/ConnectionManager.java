package pt.isel.ls.Manager;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pt.isel.ls.Server.HttpServer;

import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

public class ConnectionManager {
    private final Map<String, String> env = System.getenv();
    private final SQLServerDataSource src = new SQLServerDataSource();
    private Logger logger;

    public ConnectionManager() {
        String amb = env.get("LS_DBCONN_APP_SQLSRV");
        HashMap<String,String> map=new HashMap<>();
        logger = LoggerFactory.getLogger(HttpServer.class);
        String div [] = amb.split(";");

        for(String str: div){
            String nameAndValue[]= str.split("=");
            if(nameAndValue.length==2)
                map.put(nameAndValue[0],nameAndValue[1]);
        }

        src.setServerName(map.get("server"));
        src.setDatabaseName(map.get("database"));
        src.setUser(map.get("user"));
        src.setPassword(map.get("password"));
    }

    public Connection getConection(){
        try {
            return src.getConnection();
        } catch (SQLServerException e) {
            logger.info("Cannot get connection at: " + src.getServerName() + "DB name: " + src.getDatabaseName());
            return null;
        }
    }
}
