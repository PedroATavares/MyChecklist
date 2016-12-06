package pt.isel.ls.Commands;

import pt.isel.ls.Logic.Arguments;
import pt.isel.ls.Model.CheckList;
import pt.isel.ls.Model.Tag;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class GetTagsByID implements Command {
    @Override
    public Tag execute(Arguments args, Connection con) throws SQLException, ParseException {
        int tid = Integer.parseInt( args.variableParameters.get("{tid}"));

        PreparedStatement stm = con.prepareStatement("select * from Tag where(tag.gid=?)");
        stm.setInt(1,  tid );
        ResultSet rs = stm.executeQuery();
        Tag t = new Tag(rs.getInt("gid"), rs.getString("Name"), rs.getString("Color"),null);
        return  t;
    }
}
