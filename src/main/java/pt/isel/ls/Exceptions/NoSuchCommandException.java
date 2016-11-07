package pt.isel.ls.Exceptions;

public class NoSuchCommandException extends Exception{

    public NoSuchCommandException(String path)
    {
        super("Cannot find Command in path: " + path);
    }

}
