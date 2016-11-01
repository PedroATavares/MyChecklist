package pt.isel.ls.Commands;

import pt.isel.ls.Logic.Arguments;

import java.sql.*;
import java.text.ParseException;



public class PostTags implements Command{

    @Override
    public Integer execute(Arguments args, Connection con) throws SQLException, ParseException {
        PreparedStatement stm = con.prepareStatement("insert into Tag(Name, Color)" +
                " values ( ? ,?)", Statement.RETURN_GENERATED_KEYS);
        stm.setString(1, args.arguments.get("name"));
        stm.setString(2, args.arguments.get("color"));

        stm.executeUpdate();
        ResultSet rs = stm.getGeneratedKeys();

        rs.next();
        return rs.getInt(1);
    }
}
