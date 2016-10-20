package pt.isel.ls.Commands;

import pt.isel.ls.Logic.Arguments;
import pt.isel.ls.Model.CheckList;
import pt.isel.ls.Tools.ToolsList;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class GetCheckListByID implements Command<CheckList> {

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

        PreparedStatement stm = con.prepareStatement(" select * from Checklist \n" +
                "INNER join Task on CheckList.cid = Task.cid \n" +
                "where CheckList.cid = ? ");
        stm.setInt(1, Integer.parseInt( args.variableParameters.get("{cid}") ) );
        ResultSet rs = stm.executeQuery();
        CheckList cl = null;

        if( rs.next()) {
            cl = new CheckList(rs.getInt(CheckListCid),
                    rs.getString(CheckListName),
                    rs.getString(CheckListDescript),
                    rs.getString(CheckListDuedate),
                    rs.getBoolean(CheckListIsClosed),
                    rs.getInt(CheckListTid),
                    ToolsList.makeListFromResultSet(rs));
        }
        return cl;
    }
}
