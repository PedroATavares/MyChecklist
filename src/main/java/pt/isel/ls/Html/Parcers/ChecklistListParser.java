package pt.isel.ls.Html.Parcers;

import pt.isel.ls.Model.CheckList;

import java.util.List;

public class ChecklistListParser implements HtmlParser<List<CheckList>> {
    @Override
    public String supply(List<CheckList> source) {
        return ChecklistParser.parceList(source).toHtml();
    }
}
