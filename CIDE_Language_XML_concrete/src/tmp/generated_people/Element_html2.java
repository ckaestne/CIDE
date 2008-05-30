package tmp.generated_people;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Element_html2 extends Element_html {
  public Element_html2(STag_html sTag_html, ArrayList<CMisc> cMisc1, Content_html_Seq1 content_html_Seq1, ETag_html eTag_html, ArrayList<CMisc> cMisc2, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<STag_html>("sTag_html", sTag_html),
      new PropertyZeroOrMore<CMisc>("cMisc1", cMisc1),
      new PropertyOne<Content_html_Seq1>("content_html_Seq1", content_html_Seq1),
      new PropertyOne<ETag_html>("eTag_html", eTag_html),
      new PropertyZeroOrMore<CMisc>("cMisc2", cMisc2)
    }, firstToken, lastToken);
  }
  public Element_html2(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public ASTNode deepCopy() {
    return new Element_html2(cloneProperties(),firstToken,lastToken);
  }
  public STag_html getSTag_html() {
    return ((PropertyOne<STag_html>)getProperty("sTag_html")).getValue();
  }
  public ArrayList<CMisc> getCMisc1() {
    return ((PropertyZeroOrMore<CMisc>)getProperty("cMisc1")).getValue();
  }
  public Content_html_Seq1 getContent_html_Seq1() {
    return ((PropertyOne<Content_html_Seq1>)getProperty("content_html_Seq1")).getValue();
  }
  public ETag_html getETag_html() {
    return ((PropertyOne<ETag_html>)getProperty("eTag_html")).getValue();
  }
  public ArrayList<CMisc> getCMisc2() {
    return ((PropertyZeroOrMore<CMisc>)getProperty("cMisc2")).getValue();
  }
}
