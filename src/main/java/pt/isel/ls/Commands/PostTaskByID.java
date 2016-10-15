package pt.isel.ls.Commands;


import pt.isel.ls.Logic.Arguments;

import java.sql.*;

public class PostTaskByID implements Command<Integer> {


    public PostTaskByID() {

    }

    @Override
    public Integer execute(Arguments args,Connection con) throws SQLException {

        PreparedStatement stm = con.prepareStatement("\n" +
                "insert into Task ( Name, Descrip, DueDate, cid) " +
                "values ( ?, ?, ?, ?)",
                Statement.RETURN_GENERATED_KEYS);

        stm.setString(1, args.arguments.get("name") );
        stm.setString(2, args.arguments.get("description") );
        stm.setString(3, args.arguments.get("dueDate") );
        stm.setInt(4, Integer.parseInt( args.variableParameters.get("{cid}") ) );

        stm.executeUpdate();
        ResultSet rs = stm.getGeneratedKeys();

        rs.next();
        return rs.getInt(1);
    }
}
