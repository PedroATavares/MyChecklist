package pt.isel.ls.Commands;

import pt.isel.ls.Logic.Arguments;
import pt.isel.ls.Model.CheckList;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GetCheckListsClosed implements Command {


    public GetCheckListsClosed() {

    }

    @Override
    public List<CheckList> execute(Arguments args,Connection con) throws SQLException {

        PreparedStatement stm = con.prepareStatement("select * from Checklist\n" +
                "where IsClosed = 'true'");
        ArrayList<CheckList> list = new ArrayList<CheckList>();

        ResultSet rs = stm.executeQuery();
        while (rs.next()){
            list.add(new CheckList(
                    rs.getInt("cid"),
                    rs.getString("name"),
                    rs.getString("descrip"),
                    rs.getString("dueDate"),
                    rs.getBoolean("isClosed"),
                    rs.getInt("tid"),
                    null
            ));
        }
        return  list;
    }

    @Override
    public String ToString() {
        return "GET /checklists/closed - returns a list with all completed checklists.\n";
    }
}