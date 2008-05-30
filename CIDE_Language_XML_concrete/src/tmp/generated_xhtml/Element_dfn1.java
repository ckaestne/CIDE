package tmp.generated_xhtml;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Element_dfn1 extends Element_dfn {
  public Element_dfn1(EmptyTag_dfn emptyTag_dfn, ArrayList<CMisc> cMisc, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<EmptyTag_dfn>("emptyTag_dfn", emptyTag_dfn),
      new PropertyZeroOrMore<CMisc>("cMisc", cMisc)
    }, firstToken, lastToken);
  }
  public Element_dfn1(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public ASTNode deepCopy() {
    return new Element_dfn1(cloneProperties(),firstToken,lastToken);
  }
  public EmptyTag_dfn getEmptyTag_dfn() {
    return ((PropertyOne<EmptyTag_dfn>)getProperty("emptyTag_dfn")).getValue();
  }
  public ArrayList<CMisc> getCMisc() {
    return ((PropertyZeroOrMore<CMisc>)getProperty("cMisc")).getValue();
  }
}
