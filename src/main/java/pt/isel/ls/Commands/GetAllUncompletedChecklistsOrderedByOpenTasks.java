package pt.isel.ls.Commands;


import pt.isel.ls.Logic.Arguments;
import pt.isel.ls.Model.CheckList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GetAllUncompletedChecklistsOrderedByOpenTasks implements Command<List<CheckList>> {

    //   GET /checklists/open/sorted/noftasks - returns a list with all uncompleted
    //   checklists, ordered by decreasing number of open tasks.

    Connection con;

    public GetAllUncompletedChecklistsOrderedByOpenTasks(Connection con) {
        this.con = con;
    }

    @Override
    public List<CheckList> execute(Arguments args) throws SQLException {
        PreparedStatement stm = con.prepareStatement("select checkl.* from (select checklist.cid, checklist.Descrip, Checklist.DueDate, Checklist.Name, checklist.tid, count(Checklist.cid) as che from checklist inner join task on(task.cid=checklist.cid) where task.isclosed = 'false' group by Checklist.cid, checklist.Descrip, Checklist.DueDate, Checklist.Name, checklist.tid ) as checkl order by checkl.cid DESC");
        ArrayList<CheckList> list = new ArrayList<CheckList>();

        ResultSet rs = stm.executeQuery();
        while (rs.next()){
            list.add(new CheckList(
                    rs.getInt("cid"),
                    rs.getString("name"),
                    rs.getString("descrip"),
                    rs.getString("dueDate"),
                    false,
                    rs.getInt("tid"),
                    null));
        }
        return list;
    }
}
