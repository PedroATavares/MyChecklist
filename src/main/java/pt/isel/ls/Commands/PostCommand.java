package pt.isel.ls.Commands;

import pt.isel.ls.Logic.Arguments;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;

public class PostCommand implements Command {
    private final Command cmd;
    private final String toRedirect;

    public PostCommand(Command cmd, String toRedirect) {
        this.cmd = cmd;
        this.toRedirect = toRedirect;
    }

    @Override
    public Object execute(Arguments args, Connection con) throws SQLException, ParseException, NumberFormatException {
        return cmd.execute(args,con);
    }

    @Override
    public String toString() {
        return cmd.toString();
    }

    public String getToRedirect() {
        return toRedirect;
    }
}
