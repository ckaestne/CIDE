package tmp.generated_xhtml;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Element_div2 extends Element_div {
  public Element_div2(STag_div sTag_div, ArrayList<CMisc> cMisc1, ArrayList<Content_div_Choice1> content_div_Choice1, ETag_div eTag_div, ArrayList<CMisc> cMisc2, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<STag_div>("sTag_div", sTag_div),
      new PropertyZeroOrMore<CMisc>("cMisc1", cMisc1),
      new PropertyZeroOrMore<Content_div_Choice1>("content_div_Choice1", content_div_Choice1),
      new PropertyOne<ETag_div>("eTag_div", eTag_div),
      new PropertyZeroOrMore<CMisc>("cMisc2", cMisc2)
    }, firstToken, lastToken);
  }
  public Element_div2(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public ASTNode deepCopy() {
    return new Element_div2(cloneProperties(),firstToken,lastToken);
  }
  public STag_div getSTag_div() {
    return ((PropertyOne<STag_div>)getProperty("sTag_div")).getValue();
  }
  public ArrayList<CMisc> getCMisc1() {
    return ((PropertyZeroOrMore<CMisc>)getProperty("cMisc1")).getValue();
  }
  public ArrayList<Content_div_Choice1> getContent_div_Choice1() {
    return ((PropertyZeroOrMore<Content_div_Choice1>)getProperty("content_div_Choice1")).getValue();
  }
  public ETag_div getETag_div() {
    return ((PropertyOne<ETag_div>)getProperty("eTag_div")).getValue();
  }
  public ArrayList<CMisc> getCMisc2() {
    return ((PropertyZeroOrMore<CMisc>)getProperty("cMisc2")).getValue();
  }
}
