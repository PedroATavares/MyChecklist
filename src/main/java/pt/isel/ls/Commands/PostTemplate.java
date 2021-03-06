package pt.isel.ls.Commands;


import pt.isel.ls.Logic.Arguments;
import java.sql.*;

public class PostTemplate implements Command {


    public PostTemplate() {

    }
    @Override
    public Integer execute(Arguments args,Connection con) throws SQLException {

        PreparedStatement stm = con.prepareStatement("insert into Template(Name, Descrip )" +
                " values ( ?, ?)", Statement.RETURN_GENERATED_KEYS);
        stm.setString(1, args.arguments.get("name") );
        stm.setString(2, args.arguments.get("description") );

        stm.executeUpdate();
        ResultSet rs = stm.getGeneratedKeys();

        rs.next();
        return rs.getInt(1);
    }
    @Override
    public String toString(){
        return "POST /templates - creates a new template, given the following parameters\n" +
                "name - short name.\n" +
                "description - the template description.\n";
    }
}
