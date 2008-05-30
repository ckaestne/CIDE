package tmp.generated_xhtml;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Element_pre2 extends Element_pre {
  public Element_pre2(STag_pre sTag_pre, ArrayList<CMisc> cMisc1, ArrayList<Content_pre_Choice1> content_pre_Choice1, ETag_pre eTag_pre, ArrayList<CMisc> cMisc2, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<STag_pre>("sTag_pre", sTag_pre),
      new PropertyZeroOrMore<CMisc>("cMisc1", cMisc1),
      new PropertyZeroOrMore<Content_pre_Choice1>("content_pre_Choice1", content_pre_Choice1),
      new PropertyOne<ETag_pre>("eTag_pre", eTag_pre),
      new PropertyZeroOrMore<CMisc>("cMisc2", cMisc2)
    }, firstToken, lastToken);
  }
  public Element_pre2(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public ASTNode deepCopy() {
    return new Element_pre2(cloneProperties(),firstToken,lastToken);
  }
  public STag_pre getSTag_pre() {
    return ((PropertyOne<STag_pre>)getProperty("sTag_pre")).getValue();
  }
  public ArrayList<CMisc> getCMisc1() {
    return ((PropertyZeroOrMore<CMisc>)getProperty("cMisc1")).getValue();
  }
  public ArrayList<Content_pre_Choice1> getContent_pre_Choice1() {
    return ((PropertyZeroOrMore<Content_pre_Choice1>)getProperty("content_pre_Choice1")).getValue();
  }
  public ETag_pre getETag_pre() {
    return ((PropertyOne<ETag_pre>)getProperty("eTag_pre")).getValue();
  }
  public ArrayList<CMisc> getCMisc2() {
    return ((PropertyZeroOrMore<CMisc>)getProperty("cMisc2")).getValue();
  }
}
