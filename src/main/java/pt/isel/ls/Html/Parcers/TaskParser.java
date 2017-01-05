package pt.isel.ls.Html.Parcers;

import pt.isel.ls.Html.Source.HtmlElement;
import pt.isel.ls.Model.Task;

import java.util.List;

import static pt.isel.ls.Html.Source.HtmlSupplier.*;

public class TaskParser implements HtmlParser<Task> {

    public static HtmlElement parceList(List<Task> list){
        if(list==null || list.isEmpty()) return paragraph().withText("No Tasks To Show");
        HtmlElement table = table().with(
                tr().with(
                        td().withText("Id"),
                        td().withText("CheckList Id"),
                        td().withText("Name"),
                        td().withText("Descricao"),
                        td().withText("Due Date"),
                        td().withText("Is Closed")
                )
        ).withAttribute(attribute("style", "width:50%"),
                attribute("border", "1"));

        for (Task t: list) {
            table.with(makeRow(t));
        }

        return table;

    }

    private static HtmlElement makeRow(Task t) {
        return tr().with(
                td().withText(String.valueOf(t.id)),
                td().withText(String.valueOf(t.checklistId)),
                td().withText(t.name),
                td().withText(t.description),
                td().withText(t.dueDate),
                td().with(form("/checklists/" + t.checklistId +"/tasks/"+ t.id)
                        .with(inputSubmit(t.isClosed ? "True" : "False"))
                        .with(hiddenInput("" + !t.isClosed,"isClosed"))
                        .with(hiddenInput("/checklists/" + t.checklistId,"reload"))
                )
        );
    }


    @Override
    public String supply(Task source) {
       return html().with(body().with(
                h3().withText("TASK"),
                paragraph().withText("Id: " + source.id ),
                paragraph().withText("CheckListID: " + source.checklistId),
                paragraph().withText("Description: " + source.description),
                paragraph().withText("DueDate: " + source.dueDate)
        )).toHtml();


    }
}
