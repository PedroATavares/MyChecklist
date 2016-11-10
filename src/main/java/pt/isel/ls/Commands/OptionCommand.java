package pt.isel.ls.Commands;

import pt.isel.ls.Logic.Arguments;
import pt.isel.ls.Logic.TreeNode;
import pt.isel.ls.Manager.CommandManager;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Map;
import java.util.Set;

public class OptionCommand implements Command {
    CommandManager manager;

    public OptionCommand(CommandManager manager) {
        this.manager = manager;
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
            System.out.println(cmd.toString());
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
