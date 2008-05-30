package tmp.generated_xhtml;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Element_bdo2 extends Element_bdo {
  public Element_bdo2(STag_bdo sTag_bdo, ArrayList<CMisc> cMisc1, ArrayList<Content_bdo_Choice1> content_bdo_Choice1, ETag_bdo eTag_bdo, ArrayList<CMisc> cMisc2, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<STag_bdo>("sTag_bdo", sTag_bdo),
      new PropertyZeroOrMore<CMisc>("cMisc1", cMisc1),
      new PropertyZeroOrMore<Content_bdo_Choice1>("content_bdo_Choice1", content_bdo_Choice1),
      new PropertyOne<ETag_bdo>("eTag_bdo", eTag_bdo),
      new PropertyZeroOrMore<CMisc>("cMisc2", cMisc2)
    }, firstToken, lastToken);
  }
  public Element_bdo2(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public ASTNode deepCopy() {
    return new Element_bdo2(cloneProperties(),firstToken,lastToken);
  }
  public STag_bdo getSTag_bdo() {
    return ((PropertyOne<STag_bdo>)getProperty("sTag_bdo")).getValue();
  }
  public ArrayList<CMisc> getCMisc1() {
    return ((PropertyZeroOrMore<CMisc>)getProperty("cMisc1")).getValue();
  }
  public ArrayList<Content_bdo_Choice1> getContent_bdo_Choice1() {
    return ((PropertyZeroOrMore<Content_bdo_Choice1>)getProperty("content_bdo_Choice1")).getValue();
  }
  public ETag_bdo getETag_bdo() {
    return ((PropertyOne<ETag_bdo>)getProperty("eTag_bdo")).getValue();
  }
  public ArrayList<CMisc> getCMisc2() {
    return ((PropertyZeroOrMore<CMisc>)getProperty("cMisc2")).getValue();
  }
}
