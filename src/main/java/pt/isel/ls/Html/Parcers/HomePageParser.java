package pt.isel.ls.Html.Parcers;

import static pt.isel.ls.Html.Source.HtmlSupplier.*;

public class HomePageParser implements HtmlParser {
    @Override
    public String supply(Object source) {
        return html().with(body().with(
                h3().with(hyperlink("Temlates","/templates")),
                h3().with(hyperlink("Checklists","/checklists")),
                h3().with(hyperlink("Tags","/tags"))
        )).toHtml();
    }
}
