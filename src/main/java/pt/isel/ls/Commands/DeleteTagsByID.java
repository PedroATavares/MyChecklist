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

        int gidarg=Integer.parseInt( args.variableParameters.get("{gid}") );
        PreparedStatement stm = con.prepareStatement(" delete from TagCheckList where gid= ? ");
        stm.setInt(1, gidarg );
        int rs = stm.executeUpdate();

        stm = con.prepareStatement(" delete from Tag where gid= ? ");
        stm.setInt(1, gidarg );
        rs = stm.executeUpdate();


        return  rs;
    }

    @Override
    public String ToString() {
        return "DELETE /tags/{gid} - deletes the tag with gid unique identifier.\n";
    }
}
