package tmp.generated_xhtml;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Element_tr2 extends Element_tr {
  public Element_tr2(STag_tr sTag_tr, ArrayList<CMisc> cMisc1, ArrayList<Content_tr_Choice1> content_tr_Choice1, ETag_tr eTag_tr, ArrayList<CMisc> cMisc2, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<STag_tr>("sTag_tr", sTag_tr),
      new PropertyZeroOrMore<CMisc>("cMisc1", cMisc1),
      new PropertyOneOrMore<Content_tr_Choice1>("content_tr_Choice1", content_tr_Choice1),
      new PropertyOne<ETag_tr>("eTag_tr", eTag_tr),
      new PropertyZeroOrMore<CMisc>("cMisc2", cMisc2)
    }, firstToken, lastToken);
  }
  public Element_tr2(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public ASTNode deepCopy() {
    return new Element_tr2(cloneProperties(),firstToken,lastToken);
  }
  public STag_tr getSTag_tr() {
    return ((PropertyOne<STag_tr>)getProperty("sTag_tr")).getValue();
  }
  public ArrayList<CMisc> getCMisc1() {
    return ((PropertyZeroOrMore<CMisc>)getProperty("cMisc1")).getValue();
  }
  public ArrayList<Content_tr_Choice1> getContent_tr_Choice1() {
    return ((PropertyOneOrMore<Content_tr_Choice1>)getProperty("content_tr_Choice1")).getValue();
  }
  public ETag_tr getETag_tr() {
    return ((PropertyOne<ETag_tr>)getProperty("eTag_tr")).getValue();
  }
  public ArrayList<CMisc> getCMisc2() {
    return ((PropertyZeroOrMore<CMisc>)getProperty("cMisc2")).getValue();
  }
}
