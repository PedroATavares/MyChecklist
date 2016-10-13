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
        sb.append("id: ");
        sb.append(id);
        sb.append('\n');
        sb.append("Name: ");
        sb.append(name);
        sb.append('\n');
        sb.append("Description: ");
        sb.append(description);
        sb.append('\n');
        return sb.toString();
    }
}
