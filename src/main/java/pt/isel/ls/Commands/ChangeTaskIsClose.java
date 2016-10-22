package pt.isel.ls.Commands;

import pt.isel.ls.Logic.Arguments;

import java.sql.*;

public class ChangeTaskIsClose implements Command {


    public ChangeTaskIsClose() {
    }

    @Override
    public String execute(Arguments args,Connection con) throws SQLException {

        String cid = args.variableParameters.get("{cid}");
        String lid = args.variableParameters.get("{lid}");
        String isClose = args.arguments.get("isClosed");

        PreparedStatement stm1 = con.prepareStatement("UPDATE Task\n" +
                "SET isClosed = ? \n" +
                "WHERE cid = ? AND lid = ?");

        stm1.setString(1,isClose );
        stm1.setInt(2, Integer.parseInt(cid) );
        stm1.setInt(3, Integer.parseInt( lid ) );
        stm1.executeUpdate();

        if( allTasksClosed( cid ,con)){
            PreparedStatement stm2 = con.prepareStatement("UPDATE Checklist\n" +
                    "SET IsClosed='true'\n" +
                    "WHERE cid= ?;\n");
            stm2.setInt(1, Integer.parseInt(cid) );
            stm2.executeUpdate();
        }

        return isClose ;
   }

    private boolean allTasksClosed(String cid,Connection con) throws SQLException {
        PreparedStatement stm = con.prepareStatement("select Task.isClosed from " +
                "Checklist inner join Task\n" +
                "on Checklist.cid = Task.cid\n" +
                "where Checklist.cid = ?");
        stm.setInt(1, Integer.parseInt(cid) );
        ResultSet rs = stm.executeQuery();

        while (rs.next()){
            if( !rs.getBoolean("isClosed") )
                return false;
        }
        return true;
    }




}
