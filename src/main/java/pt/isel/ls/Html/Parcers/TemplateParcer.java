package pt.isel.ls.Html.Parcers;

import pt.isel.ls.Model.Template;

import static pt.isel.ls.Html.Source.HtmlSupplier.*;
import static pt.isel.ls.Html.Source.HtmlSupplier.paragraph;

public class TemplateParcer implements HtmlParcer<Template> {

    @Override
    public String supply(Template source) {
        return html().with(body().with(
                h3().withText("TEMPLATE"),
                paragraph().withText("Id: " + source.id ),
                paragraph().withText("Name: " + source.name),
                paragraph().withText("Description: " + source.description)
        )).toHtml();
    }

}
