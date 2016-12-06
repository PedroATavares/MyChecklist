package pt.isel.ls.Commands;

import pt.isel.ls.Logic.Arguments;
import pt.isel.ls.Model.Tag;
import pt.isel.ls.Tools.ToolsList;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;

public class GetTagsByID implements Command {
    @Override
    public Tag execute(Arguments args, Connection con) throws SQLException, ParseException {
        int gid = Integer.parseInt( args.variableParameters.get("{gid}"));

        PreparedStatement stm1 = con.prepareStatement("select * from Tag \n" +
                "where Tag.gid = ?");

        stm1.setInt(1,  gid );
        ResultSet rs1 = stm1.executeQuery();
        Tag tg = null;

        if( rs1.next()) {
            tg = new Tag(
                    rs1.getInt("gid"),
                    rs1.getString("Name"),
                    rs1.getString("Color"),
                    null);
        }

        PreparedStatement stm2 = con.prepareStatement("select * from TagCheckList\n" +
                "inner join Checklist on Checklist.cid = TagCheckList.cid\n" +
                "where TagCheckList.gid = ? ");

        stm2.setInt(1,  gid );
        ResultSet rs2 = stm2.executeQuery();

        if(rs2.next())
            tg.setCheckLists(ToolsList.makeCheckListFromResultSet(rs2));

        return  tg;
    }

    @Override
    public String toString(){
        return "GET /tags/{gid} - returns the detailed information for the Tag identified by gid," +
                " including its CheckLists from which it was created (if any).\n";
    }
}
