package pt.isel.ls.Commands;

import pt.isel.ls.Logic.Arguments;
import pt.isel.ls.Model.CheckList;
import pt.isel.ls.Tools.ToolsList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;


public class GetChecklistsByTagID implements Command
{
    private static final int CheckListCid = 1;
    private static final int CheckListName = 2;
    private static final int CheckListDescript = 3;
    private static final int CheckListDuedate = 4;
    private static final int CheckListIsClosed = 5;
    private static final int CheckListTid = 6;

    @Override
    public List<CheckList> execute(Arguments args, Connection con) throws SQLException, ParseException {
        int gid = Integer.parseInt( args.variableParameters.get("{gid}"));

        PreparedStatement stm = con.prepareStatement("select * from Checklist\n" +
                "inner join TagCheckList on Checklist.cid = TagCheckList.cid\n" +
                "where TagCheckList.gid = ? ");
        stm.setInt(1,  gid );
        ArrayList<CheckList> list = new ArrayList<CheckList>();
        ResultSet rs = stm.executeQuery();
        while (rs.next()){
            list.add(new CheckList(
                    rs.getInt("cid"),
                    rs.getString("name"),
                    rs.getString("descrip"),
                    rs.getString("dueDate"),
                    rs.getBoolean("IsClosed"),
                    rs.getInt("tid"),
                    null));
        }
        //if

        return  list;
    }
    @Override
    public String toString(){
        return "GET /tags/{gid}/checklists - returns the detailed information for the checklist identified by gid," +
                " including its tasks and the template from which it was created (if any).\n";
    }
}
