package pt.isel.ls.Html.Parcers;

import pt.isel.ls.Model.Tag;

import java.util.List;

public class TagListParser implements HtmlParser<List<Tag>> {
    @Override
    public String supply(List<Tag> source) {
       return TagParser.parceList(source).toHtml();
    }
}
