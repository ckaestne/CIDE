package tmp.generated_xhtml;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Element_cite2 extends Element_cite {
  public Element_cite2(STag_cite sTag_cite, ArrayList<CMisc> cMisc1, ArrayList<Content_cite_Choice1> content_cite_Choice1, ETag_cite eTag_cite, ArrayList<CMisc> cMisc2, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<STag_cite>("sTag_cite", sTag_cite),
      new PropertyZeroOrMore<CMisc>("cMisc1", cMisc1),
      new PropertyZeroOrMore<Content_cite_Choice1>("content_cite_Choice1", content_cite_Choice1),
      new PropertyOne<ETag_cite>("eTag_cite", eTag_cite),
      new PropertyZeroOrMore<CMisc>("cMisc2", cMisc2)
    }, firstToken, lastToken);
  }
  public Element_cite2(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public ASTNode deepCopy() {
    return new Element_cite2(cloneProperties(),firstToken,lastToken);
  }
  public STag_cite getSTag_cite() {
    return ((PropertyOne<STag_cite>)getProperty("sTag_cite")).getValue();
  }
  public ArrayList<CMisc> getCMisc1() {
    return ((PropertyZeroOrMore<CMisc>)getProperty("cMisc1")).getValue();
  }
  public ArrayList<Content_cite_Choice1> getContent_cite_Choice1() {
    return ((PropertyZeroOrMore<Content_cite_Choice1>)getProperty("content_cite_Choice1")).getValue();
  }
  public ETag_cite getETag_cite() {
    return ((PropertyOne<ETag_cite>)getProperty("eTag_cite")).getValue();
  }
  public ArrayList<CMisc> getCMisc2() {
    return ((PropertyZeroOrMore<CMisc>)getProperty("cMisc2")).getValue();
  }
}
