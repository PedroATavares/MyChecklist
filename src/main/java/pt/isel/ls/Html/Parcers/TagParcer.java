package pt.isel.ls.Html.Parcers;

import pt.isel.ls.Html.Source.HtmlElement;
import pt.isel.ls.Model.Tag;

import java.util.ArrayList;

import static pt.isel.ls.Html.Source.HtmlSupplier.*;
import static pt.isel.ls.Html.Source.HtmlSupplier.paragraph;


public class TagParcer implements HtmlParcer<Tag> {

    public static HtmlElement parceList(ArrayList<Tag> tags) {
        if(tags==null || tags.isEmpty()) return paragraph().withText("No Tags To Show.");
        HtmlElement table = table().with(
                tr().with(
                        td().withText("Id"),
                        td().withText("Name"),
                        td().withText("Color")
                )
        );

        for (Tag t: tags) {
            table.with(makeRow(t));
        }

        return table;

    }


    private static HtmlElement makeRow(Tag t) {
        return tr().with(
                td().withText(String.valueOf(t.gid)),
                td().withText(t.name),
                td().withText(t.color)
        );
    }

    @Override
    public String supply(Tag source) {
        return html().with(body().with(
                h3().withText("TAG"),
                paragraph().withText("Id: " + source.gid ),
                paragraph().withText("Name: " + source.name),
                paragraph().withText("Color: " + source.color),
                h3().withText("Checklists: "),
                CheckListParcer.parceList(source.checkLists)
        )).toHtml();
    }
}
