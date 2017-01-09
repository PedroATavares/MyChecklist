package pt.isel.ls;

import org.junit.Assert;
import org.junit.Test;

import static pt.isel.ls.Html.Source.HtmlSupplier.*;

public class HtmlTests {

    @Test
    public void test_headers_paragraphs(){
        Assert.assertEquals(htmlDoc().with(html().with(
                body().with(
                        h3().withText("H1"),
                        h3().withText("H2"),
                        paragraph().withText("P1"),
                        h3().with(
                                h3().withText("Vai dar barete")
                        ).withText("Nao vai nao")
                ))).toHtml(), "<!DOCTYPE html  ><html  ><body  ><h3  >H1</h3><h3  >H2</h3>" +
                "<p  >P1</p><h3  >Nao vai nao</h3></body></html>");
    }

    @Test
    public void test_table(){
        Assert.assertEquals(
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
                )).toHtml(), "<!DOCTYPE html  ><html  ><table  ><tr  ><td  >first</td>" +
                        "<td  >second</td><td  >third</td></tr><tr  ><td  >Jill</td><td  >" +
                        "Smith</td><td  >50</td></tr></table></html>");
    }
}
