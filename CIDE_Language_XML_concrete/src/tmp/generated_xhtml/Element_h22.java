package tmp.generated_xhtml;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Element_h22 extends Element_h2 {
  public Element_h22(STag_h2 sTag_h2, ArrayList<CMisc> cMisc1, ArrayList<Content_h2_Choice1> content_h2_Choice1, ETag_h2 eTag_h2, ArrayList<CMisc> cMisc2, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<STag_h2>("sTag_h2", sTag_h2),
      new PropertyZeroOrMore<CMisc>("cMisc1", cMisc1),
      new PropertyZeroOrMore<Content_h2_Choice1>("content_h2_Choice1", content_h2_Choice1),
      new PropertyOne<ETag_h2>("eTag_h2", eTag_h2),
      new PropertyZeroOrMore<CMisc>("cMisc2", cMisc2)
    }, firstToken, lastToken);
  }
  public Element_h22(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public ASTNode deepCopy() {
    return new Element_h22(cloneProperties(),firstToken,lastToken);
  }
  public STag_h2 getSTag_h2() {
    return ((PropertyOne<STag_h2>)getProperty("sTag_h2")).getValue();
  }
  public ArrayList<CMisc> getCMisc1() {
    return ((PropertyZeroOrMore<CMisc>)getProperty("cMisc1")).getValue();
  }
  public ArrayList<Content_h2_Choice1> getContent_h2_Choice1() {
    return ((PropertyZeroOrMore<Content_h2_Choice1>)getProperty("content_h2_Choice1")).getValue();
  }
  public ETag_h2 getETag_h2() {
    return ((PropertyOne<ETag_h2>)getProperty("eTag_h2")).getValue();
  }
  public ArrayList<CMisc> getCMisc2() {
    return ((PropertyZeroOrMore<CMisc>)getProperty("cMisc2")).getValue();
  }
}
