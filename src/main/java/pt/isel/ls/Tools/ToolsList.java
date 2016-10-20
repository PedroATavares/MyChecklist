package pt.isel.ls.Tools;

import pt.isel.ls.Model.Task;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class ToolsList {

    private static final int CheckListCid = 1;
    private static final int TaskLid = 8;
    private static final int TaskName = 9;
    private static final int TaskDescript = 10;
    private static final int TaskDuedate = 11;
    private static final int TaskIsClosed = 12;

    public static ArrayList<Task> makeListFromResultSet(ResultSet rs) throws SQLException {

        ArrayList<Task> arr = new ArrayList<Task>();
        arr.add(new Task(rs.getInt(CheckListCid),rs.getInt(TaskLid),
                rs.getString(TaskName), rs.getString(TaskDescript),rs.getString(TaskDuedate),
                rs.getBoolean(TaskIsClosed)  ));

        while ( rs.next() )
        {
            arr.add(new Task(rs.getInt(CheckListCid),rs.getInt(TaskLid),
                    rs.getString(TaskName), rs.getString(TaskDescript),rs.getString(TaskDuedate),
                    rs.getBoolean(TaskIsClosed)  ));
        }
        return arr;
    }
}
