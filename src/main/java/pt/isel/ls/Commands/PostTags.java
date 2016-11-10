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

    @Override
    public String toString() {
        return "POST /tags - creates a new tag and returns its identifier, given the following parameters\n" +
                "name - the tag unique name;\n" +
                "color - a color to associate with the tag.\n";
    }
}
