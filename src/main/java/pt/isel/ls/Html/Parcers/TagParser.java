package pt.isel.ls.Html.Parcers;

import pt.isel.ls.Html.Source.HtmlElement;
import pt.isel.ls.Model.Tag;
import java.util.List;
import static pt.isel.ls.Html.Source.HtmlSupplier.*;
import static pt.isel.ls.Html.Source.HtmlSupplier.paragraph;

public class TagParser implements HtmlParser<Tag> {


    public static HtmlElement parceAllList(List<Tag> tags) {
        if(tags==null || tags.isEmpty()) return paragraph().withText("No Tags To Show.");
        HtmlElement table = table().with(
                tr().with(
                        th().withText("Id"),
                        th().withText("Name"),
                        th().withText("Color"),
                        th().withText("Link")
                )
        ).withAttribute(attribute("style", "width:50%"),
                attribute("border","1"));

        for (Tag t: tags) {
            table.with(makeAllRow(t));
        }

        return table;
    }

    public static HtmlElement parceList(List<Tag> tags) {
        if(tags==null || tags.isEmpty()) return paragraph().withText("No Tags To Show.");
        HtmlElement table = table().with(
                tr().with(
                        th().withText("Id"),
                        th().withText("Name"),
                        th().withText("Color"),
                        th().withText("Link"),
                        th().withText("Checklist of Tag")
                )
        ).withAttribute(attribute("style", "width:50%"),
                attribute("border","1"));

        for (Tag t: tags) {
            table.with(makeRow(t));
        }

        return table;
    }

    private static HtmlElement makeAllRow(Tag t) {
        return tr().with(
                td().withText(String.valueOf(t.gid)),
                td().withText(t.name),
                td().withText(t.color),
                td().with(hyperlink("Link", "/tags/" + t.gid))
        );
    }
    private static HtmlElement makeRow(Tag t) {
        return tr().with(
                td().withText(String.valueOf(t.gid)),
                td().withText(t.name),
                td().withText(t.color),
                td().with(hyperlink("Link","/tags/" + t.gid)),
                td().with(hyperlink("Link", "/tags/" + t.gid + "/checklists"))
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
                ChecklistParser.parceList(source.checkLists),
                h3().with(hyperlink("CheckLists of Tag","/tags/" + source.gid + "/checklists")),
                h3().with(hyperlink("Back","/tags")),
                h3().with(hyperlink("Home","/"))
        )).toHtml();
    }
}
