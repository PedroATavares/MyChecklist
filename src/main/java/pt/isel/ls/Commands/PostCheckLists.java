package pt.isel.ls.Commands;

import pt.isel.ls.Logic.Arguments;

import java.sql.*;

public class PostCheckLists implements Command {


    public PostCheckLists() {

    }

    @Override
    public Integer execute(Arguments args,Connection con) throws SQLException {

        PreparedStatement stm = con.prepareStatement("insert into CheckList(Name, Descrip, DueDate)" +
                " values ( ? ,?, ?)", Statement.RETURN_GENERATED_KEYS);
        stm.setString(1, args.arguments.get("name"));
        stm.setString(2, args.arguments.get("description"));
        String date= args.arguments.get("dueDate");
        if(date==null) stm.setDate(3, null);
        else
        stm.setDate(3, java.sql.Date.valueOf(args.arguments.get("dueDate")));

        stm.executeUpdate();
        ResultSet rs = stm.getGeneratedKeys();

        rs.next();
        return rs.getInt(1);
    }
}
