package pt.isel.ls.Commands;


import pt.isel.ls.Logic.Arguments;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class PostTaskByID implements Command {

    private static final int dueDatePos=1;

    public PostTaskByID() {

    }

    @Override
    public Integer execute(Arguments args,Connection con) throws SQLException, ParseException {
        Integer cid= Integer.parseInt(args.variableParameters.get("{cid}"));
        SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");
        String name= args.arguments.get("name");
        String desc= args.arguments.get("description");
        String dueDateStr= args.arguments.get("dueDate");

        try {
            con.setAutoCommit(false);

            Date dueDate=null;
            if(dueDateStr!=null) {
                dueDate = ft.parse(dueDateStr);
            }

            PreparedStatement stm = con.prepareStatement(
                    "insert into Task ( Name, Descrip, DueDate, cid) " +
                            "values ( ?, ?, ?, ?)",
                    Statement.RETURN_GENERATED_KEYS);

            stm.setString(1,name );
            stm.setString(2,desc );
            stm.setString(3, dueDateStr);
            stm.setInt(4, cid);

            stm.executeUpdate();
            ResultSet genKeys = stm.getGeneratedKeys();
            genKeys.next();

            int lid=genKeys.getInt(1);

            PreparedStatement select = con.prepareStatement("select dueDate from Checklist " +
                    "where cid= ?");
            select.setInt(1,cid);

            ResultSet selectRS = select.executeQuery();
            selectRS.next();

            Date checkDueDate = null;
            if(selectRS.getString(dueDatePos)!=null)
             checkDueDate= ft.parse(selectRS.getString(dueDatePos));

            if(checkDueDate==null && dueDate==null) {
                con.commit();
                return lid;
            }
            if(checkDueDate== null || (dueDate!=null&&checkDueDate.before(dueDate))){
                PreparedStatement update = con.prepareStatement("UPDATE Checklist \n" +
                        "SET dueDate= ? \n" +
                        "WHERE cid= ?");
                update.setString(1, dueDateStr);
                update.setInt(2, cid);
                update.executeUpdate();
            }
            con.commit();
            return lid;
        }catch (SQLException e){
            con.rollback();
            throw e;
        } catch (ParseException e) {
            con.rollback();
            System.out.print("Unparseable using " + ft);
            throw e;
        }
    }
    @Override
    public String ToString(){
        return "POST /checklists/{cid}/tasks - submits a new task for the checklist cid, given the following parameters\n"+
                "name - the task's short name.\n"+
                "description - the description.\n"+
                "dueDate - an optional due date for the task completion.\n";
    }
}
