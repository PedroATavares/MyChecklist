package pt.isel.ls.Html.Parcers;

import pt.isel.ls.Html.Source.HtmlElement;
import pt.isel.ls.Model.TemplateTask;
import java.util.List;

import static pt.isel.ls.Html.Source.HtmlSupplier.*;

public class TemplateTaskParser implements HtmlParser<TemplateTask> {

    @Override
    public String supply(TemplateTask source) {
        return html().with(
                h2().withText("TEMPLATE TASK"),
                paragraph().withText("Id: "+ source.id),
                paragraph().withText("Name: " + source.name),
                paragraph().withText("Description: " + source.description),
                paragraph().withText("Template Id: " + source.templateId)
        ).toHtml();
    }


    public static HtmlElement parceList(List<TemplateTask> templateTasks) {
        if(templateTasks==null || templateTasks.isEmpty()) return paragraph().withText("No Template Tasks To Show.");
        HtmlElement table = table().with(
                tr().with(
                        th().withText("Id"),
                        th().withText("Template Id"),
                        th().withText("Name"),
                        th().withText("Descricao")
                )
        ).withAttribute(attribute("style", "width:50%"),
                attribute("border", "1"));

        for (TemplateTask tt: templateTasks) {
            table.with(makeRow(tt));
        }

        return table;

    }

    private static HtmlElement makeRow(TemplateTask tt) {
        return tr().with(
                td().withText(String.valueOf(tt.id)),
                td().withText(String.valueOf(tt.templateId)),
                td().withText(tt.name),
                td().withText(tt.description)

        );
    }
}
