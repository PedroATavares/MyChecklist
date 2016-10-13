package pt.isel.ls.Logic;

import pt.isel.ls.Commands.Command;

import java.util.HashMap;
import java.util.Map;

public class TreeNode {
    private Map<String,TreeNode> map;
    private Command cmd;

    public TreeNode(Command cmd) {
        map =new HashMap<>();
        this.cmd = cmd;
    }

    public TreeNode() {
        map =new HashMap<>();
    }

    public Map<String, TreeNode> getMap() {
        return map;
    }

    public void setCmd(Command cmd) {
        this.cmd = cmd;
    }

    public Command getCmd() {
        return cmd;
    }
}
