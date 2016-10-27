package pt.isel.ls.Html.Parcers;

import pt.isel.ls.Model.CheckList;

import static pt.isel.ls.Html.Source.HtmlSupplier.*;

public class CheckListParcer implements HtmlParcer<CheckList>{


    @Override
    public String supply(CheckList source) {
        return html().with(
                h2().withText("Checklist"),
                paragraph().withText("Id: "+ source.id),
                paragraph().withText("Name: " + source.name),
                paragraph().withText("Description: " + source.description),
                paragraph().withText("Due date: " + source.dueDate),
                paragraph().withText("Template Id: " + source.templateId),
                paragraph().withText("Is Closed: " + source.isClosed),
                h3().withText("Tasks"),
                TaskParcer.parceList(source.tasks)
        ).toHtml();
    }
}
