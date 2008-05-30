package tmp.generated_xhtml;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Element_body2 extends Element_body {
  public Element_body2(STag_body sTag_body, ArrayList<CMisc> cMisc1, ArrayList<Content_body_Choice1> content_body_Choice1, ETag_body eTag_body, ArrayList<CMisc> cMisc2, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<STag_body>("sTag_body", sTag_body),
      new PropertyZeroOrMore<CMisc>("cMisc1", cMisc1),
      new PropertyZeroOrMore<Content_body_Choice1>("content_body_Choice1", content_body_Choice1),
      new PropertyOne<ETag_body>("eTag_body", eTag_body),
      new PropertyZeroOrMore<CMisc>("cMisc2", cMisc2)
    }, firstToken, lastToken);
  }
  public Element_body2(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public ASTNode deepCopy() {
    return new Element_body2(cloneProperties(),firstToken,lastToken);
  }
  public STag_body getSTag_body() {
    return ((PropertyOne<STag_body>)getProperty("sTag_body")).getValue();
  }
  public ArrayList<CMisc> getCMisc1() {
    return ((PropertyZeroOrMore<CMisc>)getProperty("cMisc1")).getValue();
  }
  public ArrayList<Content_body_Choice1> getContent_body_Choice1() {
    return ((PropertyZeroOrMore<Content_body_Choice1>)getProperty("content_body_Choice1")).getValue();
  }
  public ETag_body getETag_body() {
    return ((PropertyOne<ETag_body>)getProperty("eTag_body")).getValue();
  }
  public ArrayList<CMisc> getCMisc2() {
    return ((PropertyZeroOrMore<CMisc>)getProperty("cMisc2")).getValue();
  }
}
