package pt.isel.ls.Html.Source;

import java.util.ArrayList;
import java.util.List;

public class HtmlEmptyElem implements HtmlElement {
    private HtmlTag tag;
    private List<HtmlElement> children;
    String txt;

    public HtmlEmptyElem(String tag) {
        this.tag = new HtmlTag(tag);
        children= new ArrayList<>();
    }

    @Override
    public String toHtml() {
        StringBuilder sb = new StringBuilder();
        sb.append(tag.start());
        if(txt!=null) sb.append(txt);
        else
            for(HtmlElement e: children)
                sb.append(e.toHtml());
        return sb.toString();
    }

    @Override
    public HtmlElement with(HtmlElement... elems) {
        for(HtmlElement e : elems){
            children.add(e);
        }

        return this;
    }

    @Override
    public HtmlElement withText(String txt) {
        this.txt=txt;
        return this;
    }
}
