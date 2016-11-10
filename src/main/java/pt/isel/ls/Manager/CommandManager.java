package pt.isel.ls.Manager;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import pt.isel.ls.Commands.Command;
import pt.isel.ls.Commands.GetCommand;
import pt.isel.ls.Exceptions.NoSuchCommandException;
import pt.isel.ls.Logic.Arguments;
import pt.isel.ls.Logic.TreeNode;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class CommandManager {
    private  final ConnectionManager conManager= new ConnectionManager();
    private TreeNode root;
    private final String[] variables = new String[]{"{tid}","{cid}","{lid}"};
    private final Map<String,String> headers = new HashMap();
    private Map<String,TreeNode> map; // para ser acedido no comando OPTIONS

    public void searchAndExecute(String[] args){

        int parametersPos=2;

        Arguments commandArguments = new Arguments();
        Command cmd=null;
        Connection con = conManager.getConection();
        if(con == null) return;
        try {
            cmd = searchCommand(args,commandArguments);

            if(args.length>=4){
                parametersPos=3;
                fillHeaders(args[2]);
            }

            if(args.length>=3)
                fillArguments(args[parametersPos], commandArguments);

            Object result = cmd.execute(commandArguments, con);

            if(cmd instanceof GetCommand){
                GetCommand getCmd = (GetCommand) cmd;
                String resultStr = null;

                if (headers.isEmpty())
                    resultStr = getCmd.htmlParcer.supply(result);
            }

        } catch (NoSuchCommandException e) {
            System.out.print("No shuch command in path: " + args[0] + ' ' + args[1]);
            return;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } catch (ParseException e) {
            System.out.println(e.getMessage());
        }


    }

    private void fillHeaders(String arg) {
        String [] separated= arg.split("|");
        for(String div : separated) {
            String [] div2 = div.split(":");
            headers.put(div2[0],div2[1]);
        }
    }

    public void addCommand(String str, Command cmd){
        TreeNode aux;
        //Map<String,TreeNode> map;
        int i;
        String[] methodAndPath = str.split(" ");
        String[] divided = getDividedPath(methodAndPath[1]);

        if(root==null) {
            root= new TreeNode();
        }

        map= root.getMap();

        aux=map.get(methodAndPath[0]);
        if(aux==null) map.put(methodAndPath[0], aux= new TreeNode());
        map=aux.getMap();
        for (i=0;i<divided.length-1;i++){
            aux=map.get(divided[i]);
            if(aux==null) map.put(divided[i], aux= new TreeNode());
            map=aux.getMap();
        }
        aux=map.get(divided[i]);
        if(aux==null)
            map.put(divided[i],aux= new TreeNode());

        aux.setCmd(cmd);
    }

    private String[] getDividedPath(String path) {
        String[] divided  =path.split("/");
        return Arrays.copyOfRange(divided,1,divided.length);
    }

    public Command searchCommand(String[] comp,Arguments arg) throws NoSuchCommandException {
        TreeNode aux=null;
        Map<String,TreeNode> map;
        int i;
        String[] divided = getDividedPath(comp[1]);

        map= root.getMap();
        aux=map.get(comp[0]);
        if(aux==null) throw new NoSuchCommandException(comp[0] );
        map=aux.getMap();

        for (i=0;i<divided.length;i++){
            aux=map.get(divided[i]);
            if(aux==null) {
                int k;
                for (k=0;k<variables.length;k++){
                    aux=map.get(variables[k]);
                    if(aux!=null) break;
                }
                if (aux==null) throw new NoSuchCommandException(comp[0] + comp[1]);
                arg.addVariableParameter(variables[k],divided[i]);
            }
            map=aux.getMap();
        }
        Command cmd= aux.getCmd();
        if(cmd==null) throw new NoSuchCommandException(comp[0] + comp[1]);

        return cmd;
    }

    public void fillArguments(String s, Arguments arg) {
        String [] split= s.split("&");

        for(int i=0;i<split.length;i++){
            String[] pair= split[i].split("=");
            arg.addArgument(pair[0],pair[1].replace('+',' '));
        }
    }
    public Map<String,TreeNode>  getMap(){
        return map;
    }


}
