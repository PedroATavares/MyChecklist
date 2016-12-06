package pt.isel.ls.Html.Parcers;

import pt.isel.ls.Html.Source.HtmlElement;
import pt.isel.ls.Model.Template;

import java.util.List;

import static pt.isel.ls.Html.Source.HtmlSupplier.*;

public class TemplateParser implements HtmlParser<List<Template>> {

    @Override
    public String supply(List<Template> source) {
        if(source==null || source.isEmpty()) return paragraph().withText("No Templates To Show.").toHtml();
        HtmlElement table = table().with(
                tr().with(
                    h3().withText("TEMPLATE"),
                    th().withText("Id" ),
                    th().withText("Name"),
                    th().withText("Description"),
                    th().withText("Lisks")
                )
        ).withAttribute(attribute("style", "width:50%"), attribute("border", "1"));

        for (Template t: source) {
            table.with(makeRow(t));
        }
        return  html().with(body().with(
                table,
                h3().with(hyperlink("CheckLists","/checklists")),
                h3().with(hyperlink("Home","/"))
        )).toHtml();

    }

    private static HtmlElement makeRow(Template t) {
        return tr().with(
                td().withText(String.valueOf(t.id)),
                td().withText(String.valueOf(t.name)),
                td().withText(t.description),
                td().with(hyperlink("Link","/templates/" + t.id))
        );
    }
}
