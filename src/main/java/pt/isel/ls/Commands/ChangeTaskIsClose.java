package pt.isel.ls.Commands;

import pt.isel.ls.Logic.Arguments;
import pt.isel.ls.Model.CheckList;

import java.sql.*;
import java.util.ArrayList;

public class ChangeTaskIsClose implements Command<String> {

    public final Connection con;

    public ChangeTaskIsClose(Connection con) {
        this.con = con;
    }

    @Override
    public String execute(Arguments args) throws SQLException {

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

        if( allTasksClosed( cid )){
            PreparedStatement stm2 = con.prepareStatement("UPDATE Checklist\n" +
                    "SET IsClosed='true'\n" +
                    "WHERE cid= ?;\n");
            stm2.setInt(1, Integer.parseInt(cid) );
            stm2.executeUpdate();
        }

        return isClose ;
   }

    private boolean allTasksClosed(String cid) throws SQLException {
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
