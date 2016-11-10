package pt.isel.ls.Html.Source;

import java.util.ArrayList;
import java.util.List;

public class HtmlTag {
    private String tag;
    private List<HtmlAttribute> attributes;

    public HtmlTag(String tag) {
        this.tag = tag;
        attributes = new ArrayList<HtmlAttribute>();
    }

    public String start(){
        StringBuilder sb = new StringBuilder();
        sb.append('<' + tag + "  ");
        for(HtmlAttribute atr : attributes)
            sb.append(atr.toString() + ", ");
       // sb.deleteCharAt(sb.length()-1);
        sb.append('>');
        return sb.toString();
    }

    public void addAttribute(HtmlAttribute atr){
        attributes.add(atr);
    }

    public String end(){
        return "</" + tag +'>';
    }
}
