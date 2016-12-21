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
                form("/templates/" + source.temp.id + "/create").with(
                        br().withText("Name:"),
                        inputTxt("name"),
                        br().withText("Description:"),
                        inputTxt("description"),
                        br().withText("Due Date in format yyyy-mm-dd:"),
                        inputTxt("dueDate"),
                        inputSubmit()
                ),
                h3().withText("Tasks"),
                TemplateTaskParser.parceList(source.listTsk),
                form("/templates/" + source.temp.id + "/tasks").with(
                        br().withText("Name:"),
                        inputTxt("name"),
                        br().withText("Description:"),
                        inputTxt("description"),
                        inputSubmit()
                ),
                h3().with(hyperlink("Templates","/templates")),
                h3().with(hyperlink("Home","/"))
        ).toHtml();
    }


}
