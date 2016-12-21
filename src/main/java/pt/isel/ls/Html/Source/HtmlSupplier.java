package pt.isel.ls.Html.Source;

public class HtmlSupplier {

    public static HtmlElement hyperlink(String txt,String dest) {return new HtmlNestedElement("a").withText(txt)
                                                                .withAttribute(new HtmlAttribute("href",  dest));}

    public static HtmlElement form(String action){return new HtmlNestedElement("form")
            .withAttribute(new HtmlAttribute("action", action))
            .withAttribute(new HtmlAttribute("method","post"));}

    public static HtmlElement inputTxt(String name){return new HtmlNestedElement("input")
            .withAttribute(new HtmlAttribute("type", "text"))
            .withAttribute(new HtmlAttribute("name", name));}

    public static HtmlElement inputSubmit(){return new HtmlNestedElement("input")
            .withAttribute(new HtmlAttribute("type", "submit"))
            .withAttribute(new HtmlAttribute("value", "Submit"));}

    public static HtmlElement html(){return new HtmlNestedElement("html");}

    public static HtmlElement br(){return new HtmlNestedElement("br");}

    public static HtmlElement body(){return new HtmlNestedElement("body");}

    public static HtmlElement h7(){return new HtmlNestedElement("h7");}

    public static HtmlElement h6(){return new HtmlNestedElement("h6");}

    public static HtmlElement h5(){return new HtmlNestedElement("h5");}

    public static HtmlElement h4(){return new HtmlNestedElement("h4");}

    public static HtmlElement h3(){return new HtmlNestedElement("h3");}

    public static HtmlElement h2(){return new HtmlNestedElement("h2");}

    public static HtmlElement h1(){return new HtmlNestedElement("h1");}

    public static HtmlElement paragraph(){return new HtmlNestedElement("p");}

    public static HtmlElement htmlDoc(){return new HtmlEmptyElem("!DOCTYPE html");}

    public static HtmlElement table(){return new HtmlNestedElement("table");}

    public static HtmlAttribute attribute(String key,String value){return new HtmlAttribute(key, value);}

    /*Table Row*/
    public static HtmlElement tr(){return new HtmlNestedElement("tr");}

    /*Table Data*/
    public static HtmlElement td(){return new HtmlNestedElement("td");}

    /*Table Header*/
    public static HtmlElement th(){return new HtmlNestedElement("th");}

}
