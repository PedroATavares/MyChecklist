package pt.isel.ls.Html.Parcers;

import pt.isel.ls.Model.CheckList;
import java.util.List;
import static pt.isel.ls.Html.Source.HtmlSupplier.*;
import static pt.isel.ls.Html.Source.HtmlSupplier.h3;
import static pt.isel.ls.Html.Source.HtmlSupplier.hyperlink;

public class CheckListClosedParser implements HtmlParser<List<CheckList>> {

    @Override
    public String supply(List<CheckList> source) {
        return html().with(
                body().with(
                        h3().withText("Checklists"),
                        ChecklistParser.parceList(source),
                        h3().with(hyperlink("Checklists", "/checklists")),
                        h3().with(hyperlink("Home","/"))
                )).toHtml();
    }
}


