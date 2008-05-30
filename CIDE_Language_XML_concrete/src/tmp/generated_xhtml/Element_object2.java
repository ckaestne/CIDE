package tmp.generated_xhtml;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Element_object2 extends Element_object {
  public Element_object2(STag_object sTag_object, ArrayList<CMisc> cMisc1, ArrayList<Content_object_Choice1> content_object_Choice1, ETag_object eTag_object, ArrayList<CMisc> cMisc2, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<STag_object>("sTag_object", sTag_object),
      new PropertyZeroOrMore<CMisc>("cMisc1", cMisc1),
      new PropertyZeroOrMore<Content_object_Choice1>("content_object_Choice1", content_object_Choice1),
      new PropertyOne<ETag_object>("eTag_object", eTag_object),
      new PropertyZeroOrMore<CMisc>("cMisc2", cMisc2)
    }, firstToken, lastToken);
  }
  public Element_object2(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public ASTNode deepCopy() {
    return new Element_object2(cloneProperties(),firstToken,lastToken);
  }
  public STag_object getSTag_object() {
    return ((PropertyOne<STag_object>)getProperty("sTag_object")).getValue();
  }
  public ArrayList<CMisc> getCMisc1() {
    return ((PropertyZeroOrMore<CMisc>)getProperty("cMisc1")).getValue();
  }
  public ArrayList<Content_object_Choice1> getContent_object_Choice1() {
    return ((PropertyZeroOrMore<Content_object_Choice1>)getProperty("content_object_Choice1")).getValue();
  }
  public ETag_object getETag_object() {
    return ((PropertyOne<ETag_object>)getProperty("eTag_object")).getValue();
  }
  public ArrayList<CMisc> getCMisc2() {
    return ((PropertyZeroOrMore<CMisc>)getProperty("cMisc2")).getValue();
  }
}
