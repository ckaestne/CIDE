package tmp.generated_xhtml;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Element_h52 extends Element_h5 {
  public Element_h52(STag_h5 sTag_h5, ArrayList<CMisc> cMisc1, ArrayList<Content_h5_Choice1> content_h5_Choice1, ETag_h5 eTag_h5, ArrayList<CMisc> cMisc2, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<STag_h5>("sTag_h5", sTag_h5),
      new PropertyZeroOrMore<CMisc>("cMisc1", cMisc1),
      new PropertyZeroOrMore<Content_h5_Choice1>("content_h5_Choice1", content_h5_Choice1),
      new PropertyOne<ETag_h5>("eTag_h5", eTag_h5),
      new PropertyZeroOrMore<CMisc>("cMisc2", cMisc2)
    }, firstToken, lastToken);
  }
  public Element_h52(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public ASTNode deepCopy() {
    return new Element_h52(cloneProperties(),firstToken,lastToken);
  }
  public STag_h5 getSTag_h5() {
    return ((PropertyOne<STag_h5>)getProperty("sTag_h5")).getValue();
  }
  public ArrayList<CMisc> getCMisc1() {
    return ((PropertyZeroOrMore<CMisc>)getProperty("cMisc1")).getValue();
  }
  public ArrayList<Content_h5_Choice1> getContent_h5_Choice1() {
    return ((PropertyZeroOrMore<Content_h5_Choice1>)getProperty("content_h5_Choice1")).getValue();
  }
  public ETag_h5 getETag_h5() {
    return ((PropertyOne<ETag_h5>)getProperty("eTag_h5")).getValue();
  }
  public ArrayList<CMisc> getCMisc2() {
    return ((PropertyZeroOrMore<CMisc>)getProperty("cMisc2")).getValue();
  }
}
