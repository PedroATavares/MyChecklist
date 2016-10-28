package pt.isel.ls.Commands;

import pt.isel.ls.Logic.Arguments;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;

public class DeleteCheckListWithCidBygID implements Command{
    @Override
    public Integer execute(Arguments args, Connection con) throws SQLException, ParseException {
        PreparedStatement stm = con.prepareStatement("delete from TagCheckList where gid=?" +
                "delete from Tag where gid=?;");

        stm.setInt(1, Integer.parseInt( args.variableParameters.get("{gid}") ) );

        return  stm.getResultSet().getRow();
    }
}
