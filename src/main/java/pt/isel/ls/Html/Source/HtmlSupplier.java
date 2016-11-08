package pt.isel.ls.Html.Source;

public class HtmlSupplier {

    public static HtmlElement html(){return new HtmlNestedElement("html");}

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
