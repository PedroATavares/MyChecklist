package pt.isel.ls.Commands;


import pt.isel.ls.Html.Parcers.HtmlParser;
import pt.isel.ls.Json.Parcers.JsonParcer;
import pt.isel.ls.Logic.Arguments;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;

public class GetCommand implements Command {

    public final Command cmd;
    public final JsonParcer jsonParser;
    public final HtmlParser htmlParser;

    public GetCommand(Command cmd, JsonParcer jsonParser, HtmlParser htmlParser) {
        this.cmd = cmd;
        this.jsonParser = jsonParser;
        this.htmlParser = htmlParser;
    }

    @Override
    public Object execute(Arguments args, Connection con) throws SQLException, ParseException {
        return cmd.execute(args,con);
    }

    @Override
    public String toString() {
        return cmd.toString();
    }
}
