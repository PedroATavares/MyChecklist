package pt.isel.ls.Html.Parcers;

import pt.isel.ls.Html.Source.HtmlElement;
import pt.isel.ls.Model.FullTemplate;



import static pt.isel.ls.Html.Source.HtmlSupplier.*;

public class FullTemplateParcer implements HtmlParcer<FullTemplate> {

    @Override
    public String supply(FullTemplate source) {
        return html().with(
                h2().withText("FullTemplate"),
                h3().withText("Checklists"),
                CheckListParcer.parceList(source.listCkL),
                h3().withText("Tasks"),
                TemplateTaskParcer.parceList(source.listTsk)
        ).toHtml();
    }


}
