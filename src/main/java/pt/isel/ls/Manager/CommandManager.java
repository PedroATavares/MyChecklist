package pt.isel.ls.Manager;

import pt.isel.ls.Commands.Command;
import pt.isel.ls.Commands.GetCommand;
import pt.isel.ls.Exceptions.NoSuchCommandException;
import pt.isel.ls.Logic.Arguments;
import pt.isel.ls.Logic.TreeNode;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class CommandManager {
    private  final ConnectionManager conManager= new ConnectionManager();
    public TreeNode root;
    private final String[] variables = new String[]{"{tid}","{cid}","{lid}"};
    private final Map<String,String> headers = new HashMap();
    private Map<String,TreeNode> map; // para ser acedido no comando OPTIONS

    public void searchAndExecute(String[] args){

        Arguments commandArguments = new Arguments();
        Command cmd=null;
        Connection con = conManager.getConection();
        if(con == null) return;
        try {
            cmd = searchCommand(args,commandArguments);

            fillParametersAndHeaders(args, commandArguments);

            Object result = cmd.execute(commandArguments, con);

            if(cmd instanceof GetCommand){
                String resultStr = getResultString(cmd, result);
                if(resultStr!= null)
                    handlePrint(resultStr);
            }

            headers.clear();
        } catch (NoSuchCommandException e) {
            System.out.println(e.getMessage());
            return;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } catch (ParseException e) {
            System.out.println(e.getMessage());
        }


    }

    private void handlePrint(String resultStr) {
        String fileName=headers.get("file-name");
        if(fileName==null){
            System.out.println(resultStr);
            return;
        }

        PrintWriter writer = null;
        try {
            writer = new PrintWriter(fileName);
            writer.println(resultStr);
            writer.close();
        } catch (FileNotFoundException e) {
            System.out.println("Cannot write in file: " + fileName);
        }
    }

    private String getResultString(Command cmd, Object result) {
        GetCommand getCmd = (GetCommand) cmd;
        String accept = headers.get("accept");

        String resultStr = null;
        if(accept == null ){
            resultStr = getCmd.htmlParser.supply(result);
        }else {
            if(accept.equals("text/plain")){
                resultStr= result.toString();
            }else{
                if(accept.equals("application/json")){
                    resultStr = getCmd.jsonParser.supply(result).toJson();
                }else {
                    if(accept.equals("text/html")) {
                        resultStr = getCmd.htmlParser.supply(result);
                    } else
                        System.out.println( "accept:" + accept + " not recgonized");
                }
            }
        }

        return resultStr;
    }

    private void fillParametersAndHeaders(String[] args, Arguments commandArguments) {
        int parametersPos=2;

        if(args.length>=4){
            parametersPos=3;
            fillHeaders(args[2]);
        }

        if(args.length>=3 && !isHeaders(args[parametersPos]))
            fillArguments(args[parametersPos], commandArguments);
        else
            if(args.length>=3 && isHeaders(args[parametersPos])){
                fillHeaders(args[parametersPos]);
            }
    }

    private boolean isHeaders(String arg) {
        String [] split= arg.split(":");
        return split.length!=1;
    }

    private void fillHeaders(String arg) {
        String [] separated= arg.split("\\|");
        for(String div : separated) {
            String [] div2 = div.split(":");
            headers.put(div2[0],div2[1]);
        }
    }

    public void addCommand(String str, Command cmd){
        TreeNode aux;
        Map<String,TreeNode> map;
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
        if(divided.length!=0)
            aux=map.get(divided[i]);

        if(aux==null)
            map.put(divided[i],aux= new TreeNode());

        aux.setCmd(cmd);
    }

    private String[] getDividedPath(String path) {
        return path.split("/");

    }

    public Command searchCommand(String[] comp,Arguments arg) throws NoSuchCommandException {
        TreeNode aux=null;
        Map<String,TreeNode> map;
        int i;

        if(comp.length<2) throw new NoSuchCommandException(comp[0] );

        String[] divided = getDividedPath(comp[1]);

        map= root.getMap();
        aux=map.get(comp[0]);
        if(aux==null) throw new NoSuchCommandException(comp[0] );
        map=aux.getMap();

        for (i=0;i<divided.length;i++){
            aux=map.get(divided[i]);
            if(aux==null) {
                Set<String> set=map.keySet();
                Iterator<String> iter = set.iterator();
                String variable=null;
                while(iter.hasNext()){
                    String key = iter.next();
                    if(key.contains("{")) {
                        variable=key;
                        break;
                    }
                }

                if (variable== null) throw new NoSuchCommandException(comp[0] + comp[1]);

                aux=map.get(variable);
                arg.addVariableParameter(variable,divided[i]);
                /*
                int k;
                for (k=0;k<variables.length;k++){
                    aux=map.get(variables[k]);
                    if(aux!=null) break;
                }
                if (aux==null) throw new NoSuchCommandException(comp[0] + comp[1]);
                arg.addVariableParameter(variables[k],divided[i]);
                */
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

}
