package pt.isel.ls.Html.Source;

public class HtmlAttribute {
    private final String key;
    private final String value;

    public HtmlAttribute(String key, String value) {
        this.key = key;
        this.value = value;
    }

    @Override
    public String toString() {
        return key + "= \""+ value + '\"';
    }
}
