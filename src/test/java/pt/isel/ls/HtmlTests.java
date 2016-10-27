package pt.isel.ls;

import org.junit.Test;

import static pt.isel.ls.Html.Source.HtmlSupplier.*;

public class HtmlTests {

    @Test
    public void test_headers_paragraphs(){
        System.out.println(
                htmlDoc().with(html().with(
                        body().with(
                                h3().withText("H1"),
                                h3().withText("H2"),
                                paragraph().withText("P1"),
                                h3().with(
                                        h3().withText("Vai dar barete")
                                ).withText("Nao vai nao")
                        ))).toHtml());
    }

    @Test
    public void test_table(){
        System.out.println(
                htmlDoc().with(html().with(
                   table().with(
                           tr().with(
                                   td().withText("first"),
                                   td().withText("second"),
                                   td().withText("third")
                           ),
                           tr().with(
                                   td().withText("Jill"),
                                   td().withText("Smith"),
                                   td().withText("50")
                           )
                   )
                )).toHtml());
    }
}
