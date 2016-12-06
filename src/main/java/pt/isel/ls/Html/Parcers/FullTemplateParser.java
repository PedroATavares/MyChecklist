package pt.isel.ls.Html.Parcers;

import pt.isel.ls.Model.FullTemplate;


import static pt.isel.ls.Html.Source.HtmlSupplier.*;

public class FullTemplateParser implements HtmlParser<FullTemplate> {

    @Override
    public String supply(FullTemplate source) {
        return html().with(
                h2().withText("FullTemplate"),
                h3().withText("Checklists"),
                ChecklistParser.parceList(source.listCkL),
                h3().withText("Tasks"),
                TemplateTaskParser.parceList(source.listTsk),
                h3().with(hyperlink("Templates","/templates"))
        ).toHtml();
    }


}
