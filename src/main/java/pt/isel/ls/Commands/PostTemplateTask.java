package pt.isel.ls.Commands;

import pt.isel.ls.Logic.Arguments;
import java.sql.*;

public class PostTemplateTask implements Command {

    public PostTemplateTask() {
    }

    @Override
    public Integer execute(Arguments args,Connection con) throws SQLException {

        PreparedStatement stm = con.prepareStatement("insert into TemplateTask(Name, Descrip, tid)" +
                " values (? , ?, ? )", Statement.RETURN_GENERATED_KEYS);
        stm.setString(1, args.arguments.get("name") );
        stm.setString(2, args.arguments.get("description") );
        stm.setInt(3, Integer.parseInt( args.variableParameters.get("{tid}")) );

        stm.executeUpdate();
        ResultSet rs = stm.getGeneratedKeys();

        rs.next();
        return rs.getInt(1);
    }

    @Override
    public String ToString(){
        return "POST /templates/{tid}/tasks - submits a new task for the format of the template tid, given the following parameters\n" +
                "name - the task's short name.\n" +
                "description - the description.\n";
    }
}
