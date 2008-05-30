package tmp.generated_xhtml;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Element_noscript2 extends Element_noscript {
  public Element_noscript2(STag_noscript sTag_noscript, ArrayList<CMisc> cMisc1, ArrayList<Content_noscript_Choice1> content_noscript_Choice1, ETag_noscript eTag_noscript, ArrayList<CMisc> cMisc2, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<STag_noscript>("sTag_noscript", sTag_noscript),
      new PropertyZeroOrMore<CMisc>("cMisc1", cMisc1),
      new PropertyZeroOrMore<Content_noscript_Choice1>("content_noscript_Choice1", content_noscript_Choice1),
      new PropertyOne<ETag_noscript>("eTag_noscript", eTag_noscript),
      new PropertyZeroOrMore<CMisc>("cMisc2", cMisc2)
    }, firstToken, lastToken);
  }
  public Element_noscript2(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public ASTNode deepCopy() {
    return new Element_noscript2(cloneProperties(),firstToken,lastToken);
  }
  public STag_noscript getSTag_noscript() {
    return ((PropertyOne<STag_noscript>)getProperty("sTag_noscript")).getValue();
  }
  public ArrayList<CMisc> getCMisc1() {
    return ((PropertyZeroOrMore<CMisc>)getProperty("cMisc1")).getValue();
  }
  public ArrayList<Content_noscript_Choice1> getContent_noscript_Choice1() {
    return ((PropertyZeroOrMore<Content_noscript_Choice1>)getProperty("content_noscript_Choice1")).getValue();
  }
  public ETag_noscript getETag_noscript() {
    return ((PropertyOne<ETag_noscript>)getProperty("eTag_noscript")).getValue();
  }
  public ArrayList<CMisc> getCMisc2() {
    return ((PropertyZeroOrMore<CMisc>)getProperty("cMisc2")).getValue();
  }
}
