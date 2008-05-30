package tmp.generated_xhtml;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Element_sub2 extends Element_sub {
  public Element_sub2(STag_sub sTag_sub, ArrayList<CMisc> cMisc1, ArrayList<Content_sub_Choice1> content_sub_Choice1, ETag_sub eTag_sub, ArrayList<CMisc> cMisc2, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<STag_sub>("sTag_sub", sTag_sub),
      new PropertyZeroOrMore<CMisc>("cMisc1", cMisc1),
      new PropertyZeroOrMore<Content_sub_Choice1>("content_sub_Choice1", content_sub_Choice1),
      new PropertyOne<ETag_sub>("eTag_sub", eTag_sub),
      new PropertyZeroOrMore<CMisc>("cMisc2", cMisc2)
    }, firstToken, lastToken);
  }
  public Element_sub2(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public ASTNode deepCopy() {
    return new Element_sub2(cloneProperties(),firstToken,lastToken);
  }
  public STag_sub getSTag_sub() {
    return ((PropertyOne<STag_sub>)getProperty("sTag_sub")).getValue();
  }
  public ArrayList<CMisc> getCMisc1() {
    return ((PropertyZeroOrMore<CMisc>)getProperty("cMisc1")).getValue();
  }
  public ArrayList<Content_sub_Choice1> getContent_sub_Choice1() {
    return ((PropertyZeroOrMore<Content_sub_Choice1>)getProperty("content_sub_Choice1")).getValue();
  }
  public ETag_sub getETag_sub() {
    return ((PropertyOne<ETag_sub>)getProperty("eTag_sub")).getValue();
  }
  public ArrayList<CMisc> getCMisc2() {
    return ((PropertyZeroOrMore<CMisc>)getProperty("cMisc2")).getValue();
  }
}
