package tmp.generated_xhtml;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Element_big2 extends Element_big {
  public Element_big2(STag_big sTag_big, ArrayList<CMisc> cMisc1, ArrayList<Content_big_Choice1> content_big_Choice1, ETag_big eTag_big, ArrayList<CMisc> cMisc2, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<STag_big>("sTag_big", sTag_big),
      new PropertyZeroOrMore<CMisc>("cMisc1", cMisc1),
      new PropertyZeroOrMore<Content_big_Choice1>("content_big_Choice1", content_big_Choice1),
      new PropertyOne<ETag_big>("eTag_big", eTag_big),
      new PropertyZeroOrMore<CMisc>("cMisc2", cMisc2)
    }, firstToken, lastToken);
  }
  public Element_big2(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public ASTNode deepCopy() {
    return new Element_big2(cloneProperties(),firstToken,lastToken);
  }
  public STag_big getSTag_big() {
    return ((PropertyOne<STag_big>)getProperty("sTag_big")).getValue();
  }
  public ArrayList<CMisc> getCMisc1() {
    return ((PropertyZeroOrMore<CMisc>)getProperty("cMisc1")).getValue();
  }
  public ArrayList<Content_big_Choice1> getContent_big_Choice1() {
    return ((PropertyZeroOrMore<Content_big_Choice1>)getProperty("content_big_Choice1")).getValue();
  }
  public ETag_big getETag_big() {
    return ((PropertyOne<ETag_big>)getProperty("eTag_big")).getValue();
  }
  public ArrayList<CMisc> getCMisc2() {
    return ((PropertyZeroOrMore<CMisc>)getProperty("cMisc2")).getValue();
  }
}
