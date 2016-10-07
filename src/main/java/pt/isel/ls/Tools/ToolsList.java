package pt.isel.ls.Tools;

import pt.isel.ls.Model.Task;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class ToolsList {

    public static ArrayList<Task> makeListFromResultSet(ResultSet rs) throws SQLException {

        ArrayList<Task> arr = new ArrayList<Task>();
        arr.add(new Task(rs.getInt(1),rs.getInt(7), rs.getString(8), rs.getString(9),rs.getString(10),rs.getBoolean(11)));

        while ( rs.next() )
        {
            arr.add(new Task(rs.getInt(1),rs.getInt(7), rs.getString(8), rs.getString(9),rs.getString(10),rs.getBoolean(11)));
        }
        return arr;
    }
}
