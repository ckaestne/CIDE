package tmp.generated_xhtml;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Element_noscript1 extends Element_noscript {
  public Element_noscript1(EmptyTag_noscript emptyTag_noscript, ArrayList<CMisc> cMisc, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<EmptyTag_noscript>("emptyTag_noscript", emptyTag_noscript),
      new PropertyZeroOrMore<CMisc>("cMisc", cMisc)
    }, firstToken, lastToken);
  }
  public Element_noscript1(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public ASTNode deepCopy() {
    return new Element_noscript1(cloneProperties(),firstToken,lastToken);
  }
  public EmptyTag_noscript getEmptyTag_noscript() {
    return ((PropertyOne<EmptyTag_noscript>)getProperty("emptyTag_noscript")).getValue();
  }
  public ArrayList<CMisc> getCMisc() {
    return ((PropertyZeroOrMore<CMisc>)getProperty("cMisc")).getValue();
  }
}
