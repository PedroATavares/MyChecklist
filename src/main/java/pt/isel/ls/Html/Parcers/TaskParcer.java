package pt.isel.ls.Html.Parcers;

import pt.isel.ls.Html.Source.HtmlElement;
import pt.isel.ls.Html.Source.HtmlNestedElement;
import pt.isel.ls.Model.Task;

import java.util.List;

import static pt.isel.ls.Html.Source.HtmlSupplier.*;

public class TaskParcer implements HtmlParcer<Task> {

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
        );

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
                td().withText(String.valueOf(t.isClosed))
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