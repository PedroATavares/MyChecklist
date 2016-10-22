package pt.isel.ls.Model;

public class Template {

    public final Integer id;
    public final String name;
    public final String description;

    public Template(Integer id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }
    @Override
    public String toString(){
        StringBuilder sb=new StringBuilder();
        sb.append("Template id: ");
        sb.append(id);
        sb.append("\n\tName: ");
        sb.append(name);
        sb.append("\n\tDescription: ");
        sb.append(description);
        sb.append('\n');
        return sb.toString();
    }
}
