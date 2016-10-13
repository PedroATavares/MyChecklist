package pt.isel.ls.Commands;

import pt.isel.ls.Logic.Arguments;

import java.sql.*;

public class PostCheckLists implements Command<Integer> {
    public final Connection con;

    public PostCheckLists(Connection con) {
        this.con = con;
    }

    @Override
    public Integer execute(Arguments args) throws SQLException {

        PreparedStatement stm = con.prepareStatement("insert into CheckList(Name, Descrip, DueDate)" +
                " values ( ? ,?, ?)", Statement.RETURN_GENERATED_KEYS);
        stm.setString(1, args.arguments.get("name"));
        stm.setString(2, args.arguments.get("description"));
        stm.setDate(3, java.sql.Date.valueOf(args.arguments.get("dueDate")));

        stm.executeUpdate();
        ResultSet rs = stm.getGeneratedKeys();

        rs.next();
        return rs.getInt(1);
    }
}
