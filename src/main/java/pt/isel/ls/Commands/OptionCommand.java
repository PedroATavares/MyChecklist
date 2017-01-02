package pt.isel.ls.Commands;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pt.isel.ls.Logic.Arguments;
import pt.isel.ls.Logic.TreeNode;
import pt.isel.ls.Manager.CommandManager;
import pt.isel.ls.Server.HttpServer;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Map;
import java.util.Set;

public class OptionCommand implements Command {
    CommandManager manager;
    private Logger logger;

    public OptionCommand(CommandManager manager) {
        this.manager = manager;
        logger = LoggerFactory.getLogger(HttpServer.class);
    }

    @Override
    public Object execute(Arguments args, Connection con) throws SQLException, ParseException {
        TreeNode root = manager.root;
        searchAndPrint(root);
        return 0;
    }

    private void searchAndPrint(TreeNode root){
        Map<String,TreeNode> map=root.getMap();
        Command cmd= root.getCmd();
        if(cmd !=null) {
            logger.info(cmd.toString());
        }
        if(map.isEmpty()) return;

        map.forEach((str,node)->{
            searchAndPrint(node);
        });

    }

    @Override
    public String toString() {
       return "OPTION / - Shows all available commands";
    }
}
