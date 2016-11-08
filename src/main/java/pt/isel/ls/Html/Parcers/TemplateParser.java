package pt.isel.ls.Html.Parcers;

import pt.isel.ls.Html.Source.HtmlElement;
import pt.isel.ls.Model.Template;

import java.util.List;

import static pt.isel.ls.Html.Source.HtmlSupplier.*;

public class TemplateParser implements HtmlParser<List<Template>> {


    public String supply(List<Template> templates) {
        if (templates == null || templates.isEmpty()) return "No CheckLists To Show.";
        HtmlElement table = table().with(
                tr().with(
                        th().withText("Id"),
                        th().withText("Name"),
                        th().withText("Descricao")
                )
        );

        for (Template t : templates) {
            table.with(makeRow(t));
        }

        return table.toHtml();
    }

    private static HtmlElement makeRow(Template t) {
        return tr().with(
                td().withText(String.valueOf(t.id)),
                td().withText(String.valueOf(t.name)),
                td().withText(t.description)

        );
    }
}
