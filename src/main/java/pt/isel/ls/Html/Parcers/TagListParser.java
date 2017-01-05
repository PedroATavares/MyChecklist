package pt.isel.ls.Html.Parcers;

import pt.isel.ls.Model.Tag;

import java.util.List;

import static pt.isel.ls.Html.Source.HtmlSupplier.*;

public class TagListParser implements HtmlParser<List<Tag>> {
    @Override
    public String supply(List<Tag> source) {
        return html().with(
                body().with(
                        h3().withText("Tags"),
                        TagAllParser.parceList(source),
                        form("/tags").with(
                                br().withText("Name:"),
                                inputTxt("name"),
                                br().withText("Color:"),
                                inputTxt("color"),
                                hiddenInput("/tags/","dest"),
                                inputSubmit("Submit")
                        ),
                        h3().with(hyperlink("Home","/"))
                )).toHtml();
    }
}
