package tmp.generated_xhtml;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Element_h32 extends Element_h3 {
  public Element_h32(STag_h3 sTag_h3, ArrayList<CMisc> cMisc1, ArrayList<Content_h3_Choice1> content_h3_Choice1, ETag_h3 eTag_h3, ArrayList<CMisc> cMisc2, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<STag_h3>("sTag_h3", sTag_h3),
      new PropertyZeroOrMore<CMisc>("cMisc1", cMisc1),
      new PropertyZeroOrMore<Content_h3_Choice1>("content_h3_Choice1", content_h3_Choice1),
      new PropertyOne<ETag_h3>("eTag_h3", eTag_h3),
      new PropertyZeroOrMore<CMisc>("cMisc2", cMisc2)
    }, firstToken, lastToken);
  }
  public Element_h32(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public ASTNode deepCopy() {
    return new Element_h32(cloneProperties(),firstToken,lastToken);
  }
  public STag_h3 getSTag_h3() {
    return ((PropertyOne<STag_h3>)getProperty("sTag_h3")).getValue();
  }
  public ArrayList<CMisc> getCMisc1() {
    return ((PropertyZeroOrMore<CMisc>)getProperty("cMisc1")).getValue();
  }
  public ArrayList<Content_h3_Choice1> getContent_h3_Choice1() {
    return ((PropertyZeroOrMore<Content_h3_Choice1>)getProperty("content_h3_Choice1")).getValue();
  }
  public ETag_h3 getETag_h3() {
    return ((PropertyOne<ETag_h3>)getProperty("eTag_h3")).getValue();
  }
  public ArrayList<CMisc> getCMisc2() {
    return ((PropertyZeroOrMore<CMisc>)getProperty("cMisc2")).getValue();
  }
}
