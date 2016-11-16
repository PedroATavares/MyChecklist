package pt.isel.ls.Commands;

import pt.isel.ls.Logic.Arguments;
import pt.isel.ls.Model.CheckList;
import pt.isel.ls.Tools.ToolsList;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;



public class GetCheckListByID implements Command {
    private static final int CheckListCid = 1;
    private static final int CheckListName = 2;
    private static final int CheckListDescript = 3;
    private static final int CheckListDuedate = 4;
    private static final int CheckListIsClosed = 5;
    private static final int CheckListTid = 6;

    public GetCheckListByID() {

    }

    @Override
    public CheckList execute(Arguments args,Connection con) throws SQLException {
        int cid = Integer.parseInt( args.variableParameters.get("{cid}"));

        PreparedStatement stm1 = con.prepareStatement("select * from Checklist\n" +
                "where cid = ?");
        stm1.setInt(1,  cid );
        ResultSet rs1 = stm1.executeQuery();
        CheckList cl = null;

        if( rs1.next()) {
            cl = new CheckList(rs1.getInt(CheckListCid),
                    rs1.getString(CheckListName),
                    rs1.getString(CheckListDescript),
                    rs1.getString(CheckListDuedate),
                    rs1.getBoolean(CheckListIsClosed),
                    rs1.getInt(CheckListTid));
        }

        PreparedStatement stm2 = con.prepareStatement("select * from Checklist \n" +
                "INNER join Task on CheckList.cid = Task.cid \n" +
                "where CheckList.cid = ? ");
        stm2.setInt(1,  cid );
        ResultSet rs2 = stm2.executeQuery();
        if(rs2.next())
            cl.setTasks(ToolsList.makeTaskListFromResultSet(rs2));

        PreparedStatement stm3 = con.prepareStatement("\n" +
                "select * from TagCheckList\n" +
                "INNER JOIN Tag on Tag.gid = TagCheckList.gid\n" +
                "\twhere TagCheckList.cid = ?");
        stm3.setInt(1,  cid );
        ResultSet rs3 = stm3.executeQuery();
        if( rs3.next())
            cl.setTag(ToolsList.makeTagListFromResultSet(rs3));

        return cl;
    }
    @Override
    public String toString(){
        return "GET /checklists/{cid} - returns the detailed information for the checklist identified by cid," +
                " including its tasks and the template from which it was created (if any).\n";
    }
}
