package tmp.generated_xhtml;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Element_q2 extends Element_q {
  public Element_q2(STag_q sTag_q, ArrayList<CMisc> cMisc1, ArrayList<Content_q_Choice1> content_q_Choice1, ETag_q eTag_q, ArrayList<CMisc> cMisc2, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<STag_q>("sTag_q", sTag_q),
      new PropertyZeroOrMore<CMisc>("cMisc1", cMisc1),
      new PropertyZeroOrMore<Content_q_Choice1>("content_q_Choice1", content_q_Choice1),
      new PropertyOne<ETag_q>("eTag_q", eTag_q),
      new PropertyZeroOrMore<CMisc>("cMisc2", cMisc2)
    }, firstToken, lastToken);
  }
  public Element_q2(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public ASTNode deepCopy() {
    return new Element_q2(cloneProperties(),firstToken,lastToken);
  }
  public STag_q getSTag_q() {
    return ((PropertyOne<STag_q>)getProperty("sTag_q")).getValue();
  }
  public ArrayList<CMisc> getCMisc1() {
    return ((PropertyZeroOrMore<CMisc>)getProperty("cMisc1")).getValue();
  }
  public ArrayList<Content_q_Choice1> getContent_q_Choice1() {
    return ((PropertyZeroOrMore<Content_q_Choice1>)getProperty("content_q_Choice1")).getValue();
  }
  public ETag_q getETag_q() {
    return ((PropertyOne<ETag_q>)getProperty("eTag_q")).getValue();
  }
  public ArrayList<CMisc> getCMisc2() {
    return ((PropertyZeroOrMore<CMisc>)getProperty("cMisc2")).getValue();
  }
}
