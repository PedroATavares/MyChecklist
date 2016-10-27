package pt.isel.ls.Html.Source;

import java.util.ArrayList;
import java.util.List;

public class HtmlTag {
    private String tag;

    public HtmlTag(String tag) {
        this.tag = tag;
    }

    public String start(){
        return '<' + tag +">";
    }

    public String end(){
        return "</" + tag +'>';
    }
}
