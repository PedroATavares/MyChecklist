package pt.isel.ls.Commands;

import pt.isel.ls.Logic.Arguments;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;


public class DeleteTagsByID implements Command {
    @Override
    public Integer execute(Arguments args, Connection con) throws SQLException, ParseException {
        /*
        PreparedStatement stm = con.prepareStatement("delete from TagCheckList where gid= ? " +
                "delete from Tag where gid= ? ");

        stm.setInt(1, Integer.parseInt( args.variableParameters.get("{gid}") ) );

        stm.setInt(2, Integer.parseInt( args.variableParameters.get("{gid}") ) );
        stm.executeQuery().getRow();*/

        int gidarg=Integer.parseInt( args.variableParameters.get("{gid}") );
        PreparedStatement stm = con.prepareStatement(" delete from TagCheckList where gid= ? ");
        stm.setInt(1, gidarg );
        ResultSet rs = stm.executeQuery();

        stm = con.prepareStatement(" delete from Tag where gid= ? ");
        stm.setInt(1, gidarg );
        rs = stm.executeQuery();


        return  2;
    }
}
