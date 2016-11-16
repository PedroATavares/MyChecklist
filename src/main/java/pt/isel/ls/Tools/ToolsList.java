package pt.isel.ls.Tools;

import pt.isel.ls.Model.Tag;
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
    private static final int TagId = 1;
    private static final int TagName = 4;
    private static final int TagColor = 5;

    public static ArrayList<Task> makeTaskListFromResultSet(ResultSet rs) throws SQLException {

        ArrayList<Task> arr = new ArrayList<>();
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
    public static ArrayList<Tag> makeTagListFromResultSet(ResultSet rs) throws SQLException {

        ArrayList<Tag> arr = new ArrayList<>();
        arr.add(new Tag(rs.getInt(TagId), rs.getString(TagName), rs.getString(TagColor)));

        while ( rs.next() )
        {
            arr.add(new Tag(rs.getInt(TagId), rs.getString(TagName), rs.getString(TagColor)) );
        }
        return arr;
    }
}
