package pt.isel.ls.Html.Parcers;

import pt.isel.ls.Model.CheckList;

import java.util.List;

public class ChecklistListParser implements HtmlParcer<List<CheckList>> {
    @Override
    public String supply(List<CheckList> source) {
        return CheckListParcer.parceList(source).toHtml();
    }
}
