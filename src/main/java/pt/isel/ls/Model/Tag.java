package pt.isel.ls.Model;

import java.util.ArrayList;

public class Tag
{
    public final int gid;
    public final String name;
    public final String color;
    public ArrayList<CheckList> checkLists;


    public Tag(int gid, String name, String color, ArrayList<CheckList> checkLists){
        this.gid = gid;
        this.name = name;
        this.color = color;
        this.checkLists = checkLists;
    }
    public Tag(int gid, String name, String color){
        this.gid = gid;
        this.name = name;
        this.color = color;
    }

    @Override
    public String toString(){
        StringBuilder sb=new StringBuilder();
        sb.append("Tag gid: ");
        sb.append(gid);
        sb.append("\n\tName: ");
        sb.append(name);
        sb.append("\n\tColor: ");
        sb.append(color);
        if(checkLists!=null)
            for (int i = 0; i <checkLists.size(); i++) {
                sb.append(checkLists.get(i));
                sb.append('\n');
            }
        return sb.toString();
    }
}
