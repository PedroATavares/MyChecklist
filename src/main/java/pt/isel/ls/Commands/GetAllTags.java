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

public class GetAllTags implements Command {
    @Override
    public List<Tag> execute(Arguments args, Connection con) throws SQLException, ParseException {
        PreparedStatement stm = con.prepareStatement("select * from Tag");
        ArrayList<Tag> list = new ArrayList<Tag>();

        ResultSet rs = stm.executeQuery();
        while (rs.next()){
            list.add(new Tag(
                    rs.getInt("gid"),
                    rs.getString("name"),
                    rs.getString("color"),
                    null));
        }
        return  list;
    }
}
