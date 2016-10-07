package pt.isel.ls.Commands;

import pt.isel.ls.Logic.Arguments;
import pt.isel.ls.Model.CheckList;
import pt.isel.ls.Tools.ToolsList;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class GetCheckListByID implements Command<CheckList>
{

    public final Connection con;

    public GetCheckListByID(Connection con) {
        this.con = con;
    }

    @Override
    public CheckList execute(Arguments args) throws SQLException {

        PreparedStatement stm = con.prepareStatement(" select * from Checklist \n" +
                "INNER join Task on CheckList.cid = Task.cid \n" +
                "where CheckList.cid = ? ");
        stm.setInt(1, Integer.parseInt( args.variableParameters.get("{cid}") ) );
        ResultSet rs = stm.executeQuery();
        CheckList cl = null;

        if( rs.next()) {
            cl = new CheckList(rs.getInt(1),
                    rs.getString(2),
                    rs.getString(3),
                    rs.getString(4),
                    rs.getInt(5),
                    ToolsList.makeListFromResultSet(rs));
        }
        return cl;
    }
}
