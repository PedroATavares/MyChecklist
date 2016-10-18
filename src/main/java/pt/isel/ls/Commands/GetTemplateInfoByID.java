package pt.isel.ls.Commands;

import pt.isel.ls.Logic.Arguments;
import pt.isel.ls.Model.CheckList;
import pt.isel.ls.Model.FullTemplate;
import pt.isel.ls.Model.Template;
import pt.isel.ls.Model.TemplateTask;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GetTemplateInfoByID implements Command<FullTemplate> {


    public GetTemplateInfoByID() {

    }

    private Template tmp = null;
    private List<CheckList> listCkl = new ArrayList<CheckList>();
    private List<TemplateTask> listTsk = new ArrayList<TemplateTask>();

    @Override
    public FullTemplate execute(Arguments args,Connection con) throws SQLException {

        PreparedStatement stm1 = con.prepareStatement(" select * from Template\n" +
                "where tid = ?");
        stm1.setInt(1, Integer.parseInt( args.variableParameters.get("{tid}") ) );
        readToTemplate(stm1.executeQuery());

        PreparedStatement stm2 = con.prepareStatement("select * from TemplateTask\n" +
                "where tid = ?");
        stm2.setInt(1, Integer.parseInt( args.variableParameters.get("{tid}") ) );
        readToTemplateTask(stm2.executeQuery());

        PreparedStatement stm3 = con.prepareStatement("select * from CheckList \n" +
                "where tid = ?\n");
        stm3.setInt(1, Integer.parseInt( args.variableParameters.get("{tid}") ) );
        readToChekList(stm3.executeQuery());

        FullTemplate result = new FullTemplate(tmp, listCkl, listTsk);
        return result;
    }

    private void readToChekList(ResultSet rs) throws SQLException {
        while (rs.next()) {
            listCkl.add(new CheckList(
                    rs.getInt("cid"),
                    rs.getString("name"),
                    rs.getString("descrip"),
                    rs.getString("dueDate"),
                    rs.getBoolean("isClosed"),
                    rs.getInt("tid"), null));
        }
    }
    private void readToTemplateTask(ResultSet rs) throws SQLException {
        while (rs.next()){
            listTsk.add(new TemplateTask(
                    rs.getInt("tempTskId"),
                    rs.getInt("tid"),
                    rs.getString("name"),
                    rs.getString("descrip")
            ));
        }
    }
    private void readToTemplate(ResultSet rs) throws SQLException {
        if (rs.next()) {
            tmp = new Template(
                    rs.getInt("tid"),
                    rs.getString("name"),
                    rs.getString("descrip")
            );
        }
    }
}
