package pt.isel.ls.Html.Parcers;

import pt.isel.ls.Html.Source.HtmlElement;
import pt.isel.ls.Model.CheckList;

import java.util.ArrayList;

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
                TaskParcer.parceList(source.tasks),
                h3().withText("Tags"),
                TagParcer.parceList(source.tags)
        ).toHtml();
    }


    public static HtmlElement parceList(ArrayList<CheckList> checkLists) {
        if(checkLists==null || checkLists.isEmpty()) return paragraph().withText("No CheckLists To Show.");
        HtmlElement table = table().with(
                tr().with(
                        td().withText("Id"),
                        td().withText("Template Id"),
                        td().withText("Name"),
                        td().withText("Descricao"),
                        td().withText("Due Date"),
                        td().withText("Is Closed")
                )
        );

        for (CheckList c: checkLists) {
            table.with(makeRow(c));
        }

        return table;

    }

    private static HtmlElement makeRow(CheckList c) {
        return tr().with(
                td().withText(String.valueOf(c.id)),
                td().withText(String.valueOf(c.templateId)),
                td().withText(c.name),
                td().withText(c.description),
                td().withText(c.dueDate),
                td().withText(String.valueOf(c.isClosed))

        );
    }
}
