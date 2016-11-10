package pt.isel.ls.Html.Source;

public interface HtmlElement {

    public String toHtml();
    public HtmlElement with(HtmlElement ... elems );
    public HtmlElement withText(String txt);
    public HtmlElement withAttribute(HtmlAttribute ... attributes);
}
