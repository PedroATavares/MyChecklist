package pt.isel.ls.Commands;

import pt.isel.ls.Logic.Arguments;

import java.io.StringWriter;
import java.sql.*;

public class ChangeTaskIsClose implements Command<String> {

    public final Connection con;

    public ChangeTaskIsClose(Connection con) {
        this.con = con;
    }

    @Override
    public String execute(Arguments args) throws SQLException {

        /*PreparedStatement stm1 = con.prepareStatement("select Task.isClosed from Task\n" +
                "where cid = ? AND lid = ?");

        stm1.setInt(1, Integer.parseInt( args.variableParameters.get("{cid}") ) );
        stm1.setInt(2, Integer.parseInt( args.variableParameters.get("{lid}") ) );

        ResultSet rs1 = stm1.executeQuery();
        rs1.next();

        String isClose = "true";
        if( rs1.getString(1).equals(isClose) )
            isClose = "false";*/

        // se recebermos o true ou false nao é preciso isto em cima

        String isClose = args.arguments.get("isClosed");
        PreparedStatement stm2 = con.prepareStatement("UPDATE Task\n" +
                "SET isClosed = ? \n" +
                "WHERE cid = ? AND lid = ?");

        stm2.setString(1,isClose );
        stm2.setInt(2, Integer.parseInt( args.variableParameters.get("{cid}") ) );
        stm2.setInt(3, Integer.parseInt( args.variableParameters.get("{lid}") ) );
        stm2.executeUpdate(); // aqui sai 1 se for feito o execute com sucesso, secalhar vamos precisar

        return isClose ;
   }
}
