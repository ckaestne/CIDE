package tmp.generated_xhtml;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Element_sup2 extends Element_sup {
  public Element_sup2(STag_sup sTag_sup, ArrayList<CMisc> cMisc1, ArrayList<Content_sup_Choice1> content_sup_Choice1, ETag_sup eTag_sup, ArrayList<CMisc> cMisc2, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<STag_sup>("sTag_sup", sTag_sup),
      new PropertyZeroOrMore<CMisc>("cMisc1", cMisc1),
      new PropertyZeroOrMore<Content_sup_Choice1>("content_sup_Choice1", content_sup_Choice1),
      new PropertyOne<ETag_sup>("eTag_sup", eTag_sup),
      new PropertyZeroOrMore<CMisc>("cMisc2", cMisc2)
    }, firstToken, lastToken);
  }
  public Element_sup2(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public ASTNode deepCopy() {
    return new Element_sup2(cloneProperties(),firstToken,lastToken);
  }
  public STag_sup getSTag_sup() {
    return ((PropertyOne<STag_sup>)getProperty("sTag_sup")).getValue();
  }
  public ArrayList<CMisc> getCMisc1() {
    return ((PropertyZeroOrMore<CMisc>)getProperty("cMisc1")).getValue();
  }
  public ArrayList<Content_sup_Choice1> getContent_sup_Choice1() {
    return ((PropertyZeroOrMore<Content_sup_Choice1>)getProperty("content_sup_Choice1")).getValue();
  }
  public ETag_sup getETag_sup() {
    return ((PropertyOne<ETag_sup>)getProperty("eTag_sup")).getValue();
  }
  public ArrayList<CMisc> getCMisc2() {
    return ((PropertyZeroOrMore<CMisc>)getProperty("cMisc2")).getValue();
  }
}
