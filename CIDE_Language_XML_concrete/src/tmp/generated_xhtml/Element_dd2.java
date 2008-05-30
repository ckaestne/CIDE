package tmp.generated_xhtml;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Element_dd2 extends Element_dd {
  public Element_dd2(STag_dd sTag_dd, ArrayList<CMisc> cMisc1, ArrayList<Content_dd_Choice1> content_dd_Choice1, ETag_dd eTag_dd, ArrayList<CMisc> cMisc2, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<STag_dd>("sTag_dd", sTag_dd),
      new PropertyZeroOrMore<CMisc>("cMisc1", cMisc1),
      new PropertyZeroOrMore<Content_dd_Choice1>("content_dd_Choice1", content_dd_Choice1),
      new PropertyOne<ETag_dd>("eTag_dd", eTag_dd),
      new PropertyZeroOrMore<CMisc>("cMisc2", cMisc2)
    }, firstToken, lastToken);
  }
  public Element_dd2(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public ASTNode deepCopy() {
    return new Element_dd2(cloneProperties(),firstToken,lastToken);
  }
  public STag_dd getSTag_dd() {
    return ((PropertyOne<STag_dd>)getProperty("sTag_dd")).getValue();
  }
  public ArrayList<CMisc> getCMisc1() {
    return ((PropertyZeroOrMore<CMisc>)getProperty("cMisc1")).getValue();
  }
  public ArrayList<Content_dd_Choice1> getContent_dd_Choice1() {
    return ((PropertyZeroOrMore<Content_dd_Choice1>)getProperty("content_dd_Choice1")).getValue();
  }
  public ETag_dd getETag_dd() {
    return ((PropertyOne<ETag_dd>)getProperty("eTag_dd")).getValue();
  }
  public ArrayList<CMisc> getCMisc2() {
    return ((PropertyZeroOrMore<CMisc>)getProperty("cMisc2")).getValue();
  }
}
