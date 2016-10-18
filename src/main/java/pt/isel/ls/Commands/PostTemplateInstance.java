package pt.isel.ls.Commands;

import pt.isel.ls.Logic.Arguments;

import java.sql.*;

public class PostTemplateInstance implements Command<Integer>{

    public PostTemplateInstance() {
    }

    @Override
    public Integer execute(Arguments args, Connection con) throws SQLException {
        try {
            con.setAutoCommit(false);

            PreparedStatement selectStm = con.prepareStatement("select * from Template inner join TemplateTask\n" +
                    "on(Template.tid=TemplateTask.tid)\n" +
                    "where Template.tid=?\n" +
                    "\n",
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);

            Integer tid = Integer.parseInt(args.variableParameters.get("{tid}"));
            selectStm.setInt(1, tid);
            ResultSet selectRs = selectStm.executeQuery();
            String name = args.arguments.get("name");
            String desc = args.arguments.get("description");
            String dueDate = args.arguments.get("dueDate");

           if( !selectRs.next()) throw new SQLException("The Template: " + tid + " has no tasks");
            if (name == null)
                name = selectRs.getString(2);
            if (desc == null)
                desc = selectRs.getString(3);
            selectRs.beforeFirst();
            PreparedStatement stm2 = con.prepareStatement("insert into Checklist (Name, Descrip, DueDate, tid)" +
                            " values (?, ?, ?, ?)",
                    Statement.RETURN_GENERATED_KEYS);
            stm2.setString(1, name);
            stm2.setString(2, desc);
            stm2.setString(3, dueDate);
            stm2.setInt(4, tid);

            stm2.executeUpdate();
            ResultSet rs1= stm2.getGeneratedKeys();
            rs1.next();

            while (selectRs.next()) {
                PreparedStatement stm3 = con.prepareStatement("insert into Task (Name, Descrip, cid)" +
                        " values (?, ?, ?)");
                stm3.setString(1,selectRs.getString(6));
                stm3.setString(2,selectRs.getString(7));
                stm3.setInt(3, rs1.getInt(1));
                stm3.executeUpdate();
            }
            con.commit();
            return rs1.getInt(1);
        }catch (SQLException e){
            e.printStackTrace();
            con.rollback();
            return null;
        }
    }
}
