package tmp.generated_xhtml;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Element_em2 extends Element_em {
  public Element_em2(STag_em sTag_em, ArrayList<CMisc> cMisc1, ArrayList<Content_em_Choice1> content_em_Choice1, ETag_em eTag_em, ArrayList<CMisc> cMisc2, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<STag_em>("sTag_em", sTag_em),
      new PropertyZeroOrMore<CMisc>("cMisc1", cMisc1),
      new PropertyZeroOrMore<Content_em_Choice1>("content_em_Choice1", content_em_Choice1),
      new PropertyOne<ETag_em>("eTag_em", eTag_em),
      new PropertyZeroOrMore<CMisc>("cMisc2", cMisc2)
    }, firstToken, lastToken);
  }
  public Element_em2(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public ASTNode deepCopy() {
    return new Element_em2(cloneProperties(),firstToken,lastToken);
  }
  public STag_em getSTag_em() {
    return ((PropertyOne<STag_em>)getProperty("sTag_em")).getValue();
  }
  public ArrayList<CMisc> getCMisc1() {
    return ((PropertyZeroOrMore<CMisc>)getProperty("cMisc1")).getValue();
  }
  public ArrayList<Content_em_Choice1> getContent_em_Choice1() {
    return ((PropertyZeroOrMore<Content_em_Choice1>)getProperty("content_em_Choice1")).getValue();
  }
  public ETag_em getETag_em() {
    return ((PropertyOne<ETag_em>)getProperty("eTag_em")).getValue();
  }
  public ArrayList<CMisc> getCMisc2() {
    return ((PropertyZeroOrMore<CMisc>)getProperty("cMisc2")).getValue();
  }
}
