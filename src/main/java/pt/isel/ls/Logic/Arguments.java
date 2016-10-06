package pt.isel.ls.Logic;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by HP on 04/10/2016.
 */
public class Arguments {
   public final  Map<String,String> arguments;
   public final Map<String,String> variableParameters;

    public Arguments(Map<String, String> arguments, Map<String, String> variableParameters) {
        this.arguments = arguments;
        this.variableParameters = variableParameters;
    }

    public Arguments() {
        arguments =new HashMap<>();
        variableParameters =new HashMap<>();
    }

    public void addVariableParameter(String key, String value){
        variableParameters.put(key, value);
    }


    public void addArgument(String key, String value){
        arguments.put(key, value);
    }

}
