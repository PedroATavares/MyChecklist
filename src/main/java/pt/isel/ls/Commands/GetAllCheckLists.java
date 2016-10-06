package pt.isel.ls.Commands;

import pt.isel.ls.Logic.Arguments;
import pt.isel.ls.Model.CheckList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by HP on 04/10/2016.
 */
public class GetAllCheckLists implements Command<List<CheckList>> {
    Connection con;

    public GetAllCheckLists(Connection con) {
        this.con = con;
    }

    @Override
    public List<CheckList> execute(Arguments args) throws SQLException {

        PreparedStatement stm = con.prepareStatement("select * from checklist");
        ArrayList<CheckList> list = new ArrayList<CheckList>();

        ResultSet rs = stm.executeQuery();
        while (rs.next()){
            list.add(new CheckList(null,
                    rs.getInt("cid"),
                    rs.getString("Name"),
                    rs.getString("Descrip"),
                    rs.getString("DueDate"),
                    rs.getInt("tid")));
        }
        return  list;
    }
}
