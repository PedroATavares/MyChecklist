package pt.isel.ls.Commands;

import pt.isel.ls.Logic.Arguments;
import pt.isel.ls.Model.Template;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GetTemplates implements Command {

    public GetTemplates() {

    }

    @Override
    public List<Template> execute(Arguments args,Connection con) throws SQLException {

        PreparedStatement stm = con.prepareStatement("select * from Template");
        ArrayList<Template> list = new ArrayList<Template>();

        ResultSet rs = stm.executeQuery();
        while (rs.next()){
            list.add(new Template(
                    rs.getInt("tid"),
                    rs.getString("name"),
                    rs.getString("descrip")
            ));
        }
        return  list;
    }
}
