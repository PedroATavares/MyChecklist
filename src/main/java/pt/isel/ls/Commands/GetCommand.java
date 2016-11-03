package pt.isel.ls.Commands;

import jdk.nashorn.internal.parser.JSONParser;
import pt.isel.ls.Html.Parcers.HtmlParcer;
import pt.isel.ls.Logic.Arguments;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;

public class GetCommand implements Command {

    public final Command cmd;
    public final JSONParser jsonParser;
    public final HtmlParcer htmlParcer;

    public GetCommand(Command cmd, JSONParser jsonParser, HtmlParcer htmlParcer) {
        this.cmd = cmd;
        this.jsonParser = jsonParser;
        this.htmlParcer = htmlParcer;
    }

    @Override
    public Object execute(Arguments args, Connection con) throws SQLException, ParseException {
        return cmd.execute(args,con);
    }
}
