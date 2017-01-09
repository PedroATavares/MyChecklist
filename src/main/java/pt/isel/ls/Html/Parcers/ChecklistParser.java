package pt.isel.ls.Html.Parcers;

import pt.isel.ls.Html.Source.HtmlElement;
import pt.isel.ls.Model.CheckList;

import java.util.List;

import static pt.isel.ls.Html.Source.HtmlSupplier.*;

public class ChecklistParser implements HtmlParser<CheckList> {


    @Override
    public String supply(CheckList source) {
        HtmlElement base = html().with(
                h2().withText("Checklist"),
                paragraph().withText("Id: " + source.id),
                paragraph().withText("Name: " + source.name),
                paragraph().withText("Description: " + source.description),
                paragraph().withText("Due date: " + source.dueDate)
                );

        if (source.templateId!=0)
            base.with(
                    paragraph().with(
                            hyperlink("Template Id:" + source .templateId,"/templates/" + source.templateId)
                    )
            );

        base.with(
                paragraph().withText("Is Closed: " + source.isClosed),
                h3().withText("Tasks"),
                TaskParser.parceList(source.tasks),
                form("/checklists/" + source.id + "/tasks").with(
                        br().withText("Name:"),
                        inputTxt("name"),
                        br().withText("Description:"),
                        inputTxt("description"),
                        br().withText("Due Date in format yyyy-mm-dd:"),
                        inputTxt("dueDate"),
                        hiddenInput("/checklists/" + source.id,"reload"),
                        inputSubmit("Submit")
                ),
                h3().withText("Tags"),
                TagParser.parceList(source.tags),
                form("/checklists/" + source.id + "/tags").with(
                        br().withText("Gid:"),
                        inputTxt("gid"),
                        hiddenInput("/checklists/" + source.id,"reload"),
                        inputSubmit("Submit")
                ),
                h3().with(hyperlink("Back","./")),
                h3().with(hyperlink("Home","/"))
        );
        return base.toHtml();
    }


    public static HtmlElement parceList(List<CheckList> checkLists) {
        if(checkLists==null || checkLists.isEmpty()) return paragraph().withText("No CheckLists To Show.");
        HtmlElement table = table().with(
                tr().with(
                        th().withText("Id"),
                        th().withText("Template Id"),
                        th().withText("Name"),
                        th().withText("Description"),
                        th().withText("Due Date"),
                        th().withText("Is Closed")
                )
        ).withAttribute(attribute("style", "width:50%"),
                attribute("border", "1"));

        for (CheckList c: checkLists) {
            table.with(makeRow(c));
        }

        return table;

    }

    private static HtmlElement makeRow(CheckList c) {
        return tr().with(
                td().withText(String.valueOf(c.id)),
                td().withText((c.templateId!=null&&c.templateId!=0) ? String.valueOf(c.templateId) : "None"),
                td().with(hyperlink(c.name, "/checklists/" + c.id)),
                td().withText(c.description),
                td().withText(c.dueDate),
                td().withText(String.valueOf(c.isClosed))
        );
    }
}
