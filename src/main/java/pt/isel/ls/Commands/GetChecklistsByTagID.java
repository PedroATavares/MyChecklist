package pt.isel.ls.Commands;

import pt.isel.ls.Logic.Arguments;
import pt.isel.ls.Model.CheckList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;


public class GetChecklistsByTagID implements Command
{
    @Override
    public List<CheckList> execute(Arguments args, Connection con) throws SQLException, ParseException {
        int gid = Integer.parseInt( args.variableParameters.get("{gid}"));
        ArrayList<CheckList> list = new ArrayList<CheckList>();

        PreparedStatement stm = con.prepareStatement("select * from TagCheckList\n" +
                "where TagCheckList.gid = ? ");
        stm.setInt(1,  gid );
        ResultSet rs = stm.executeQuery();
        while (rs.next()){
            list.add(new CheckList(
                    rs.getInt("cid"),
                    rs.getString("name"),
                    rs.getString("descrip"),
                    rs.getString("dueDate"),
                    rs.getBoolean("isClosed"),
                    rs.getInt("tid"), null));
        }
        return  list;
    }
}
