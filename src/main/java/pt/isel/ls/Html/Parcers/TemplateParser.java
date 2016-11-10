package pt.isel.ls.Html.Parcers;

import pt.isel.ls.Html.Source.HtmlElement;
import pt.isel.ls.Model.Template;

import java.util.List;

import static pt.isel.ls.Html.Source.HtmlSupplier.*;

public class TemplateParser implements HtmlParser<Template> {

    @Override
    public String supply(Template source) {
        return html().with(body().with(
                h3().withText("TEMPLATE"),
                paragraph().withText("Id: " + source.id ),
                paragraph().withText("Name: " + source.name),
                paragraph().withText("Description: " + source.description)
        )).toHtml();
    }


    private static HtmlElement makeRow(Template t) {
        return tr().with(
                td().withText(String.valueOf(t.id)),
                td().withText(String.valueOf(t.name)),
                td().withText(t.description)

        );
    }
}
