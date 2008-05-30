package tmp.generated_xhtml;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Element_i2 extends Element_i {
  public Element_i2(STag_i sTag_i, ArrayList<CMisc> cMisc1, ArrayList<Content_i_Choice1> content_i_Choice1, ETag_i eTag_i, ArrayList<CMisc> cMisc2, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<STag_i>("sTag_i", sTag_i),
      new PropertyZeroOrMore<CMisc>("cMisc1", cMisc1),
      new PropertyZeroOrMore<Content_i_Choice1>("content_i_Choice1", content_i_Choice1),
      new PropertyOne<ETag_i>("eTag_i", eTag_i),
      new PropertyZeroOrMore<CMisc>("cMisc2", cMisc2)
    }, firstToken, lastToken);
  }
  public Element_i2(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public ASTNode deepCopy() {
    return new Element_i2(cloneProperties(),firstToken,lastToken);
  }
  public STag_i getSTag_i() {
    return ((PropertyOne<STag_i>)getProperty("sTag_i")).getValue();
  }
  public ArrayList<CMisc> getCMisc1() {
    return ((PropertyZeroOrMore<CMisc>)getProperty("cMisc1")).getValue();
  }
  public ArrayList<Content_i_Choice1> getContent_i_Choice1() {
    return ((PropertyZeroOrMore<Content_i_Choice1>)getProperty("content_i_Choice1")).getValue();
  }
  public ETag_i getETag_i() {
    return ((PropertyOne<ETag_i>)getProperty("eTag_i")).getValue();
  }
  public ArrayList<CMisc> getCMisc2() {
    return ((PropertyZeroOrMore<CMisc>)getProperty("cMisc2")).getValue();
  }
}
