package pt.isel.ls.Commands;

import pt.isel.ls.Logic.Arguments;

import java.sql.*;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class PostCheckLists implements Command {


    public PostCheckLists() {

    }

    @Override
    public Integer execute(Arguments args,Connection con) throws SQLException, ParseException {

        PreparedStatement stm = con.prepareStatement("insert into CheckList(Name, Descrip, DueDate)" +
                " values ( ? ,?, ?)", Statement.RETURN_GENERATED_KEYS);
        stm.setString(1, args.arguments.get("name"));
        stm.setString(2, args.arguments.get("description"));
        String dueDateStr= args.arguments.get("dueDate");

        if (dueDateStr!=null && dueDateStr!="")
            stm.setString(3,dueDateStr);
        else
            stm.setString(3, null);

        stm.executeUpdate();
        ResultSet rs = stm.getGeneratedKeys();

        rs.next();
        return rs.getInt(1);
    }
    @Override
    public String toString(){
        return "POST /checklists - creates a new checklist, given the following parameters \n"+
                "name - short name.\n"+
                "description - the checklist description.\n"+
                "dueDate - an optional due date for the completion of the checklist\n";
    }
}
