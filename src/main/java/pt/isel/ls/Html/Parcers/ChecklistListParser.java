package pt.isel.ls.Html.Parcers;

import pt.isel.ls.Model.CheckList;

import java.util.List;

import static pt.isel.ls.Html.Source.HtmlSupplier.*;

public class ChecklistListParser implements HtmlParser<List<CheckList>> {
    @Override
    public String supply(List<CheckList> source) {
        return html().with(
                body().with(
                        h3().withText("Checklists"),
                        ChecklistParser.parceList(source),
                        form("/checklists").with(
                                br().withText("Name:"),
                                inputTxt("name"),
                                br().withText("Description:"),
                                inputTxt("description"),
                                br().withText("Due Date in format yyyy-mm-dd:"),
                                inputTxt("dueDate"),
                                hiddenInput("/checklists/", "dest"),
                                inputSubmit("Submit")
                        ),
                        h3().with(hyperlink("Checklists closed", "/checklists/closed")),
                        h3().with(hyperlink("Checklists open sorted Duedate", "/checklists/open/sorted/duedate")),
                        h3().with(hyperlink("Checklists open sorted number of tasks", "/checklists/open/sorted/noftasks")),
                        h3().with(hyperlink("Templates", "/templates")),
                        h3().with(hyperlink("Home", "/"))
                )).toHtml();
    }
}
