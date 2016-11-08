package pt.isel.ls.Commands;

import pt.isel.ls.Logic.Arguments;

import java.sql.*;
import java.text.ParseException;

public class PostTagInCheckListByID  implements Command {

    public PostTagInCheckListByID(){
    }

    @Override
    public Integer execute(Arguments args, Connection con) throws SQLException, ParseException {
        String gid = args.arguments.get("gid");

        PreparedStatement stm = con.prepareStatement(
                "insert into TagCheckList(cid,gid) values(?,?)",
                Statement.RETURN_GENERATED_KEYS );

        stm.setInt(1, Integer.parseInt( args.variableParameters.get("{cid}") ) );
        stm.setString(2, gid);

        stm.executeUpdate();
        ResultSet rs = stm.getGeneratedKeys();

        rs.next();
        return rs.getInt(1);
    }

    @Override
    public String toString() {
        return "POST /checklists/{cid}/tags - associate a tag to the cid checklist, given the following parameters\n" +
                "gid- the tag unique identifier.\n";
    }
}

