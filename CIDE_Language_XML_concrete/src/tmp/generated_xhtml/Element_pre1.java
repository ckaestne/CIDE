package tmp.generated_xhtml;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Element_pre1 extends Element_pre {
  public Element_pre1(EmptyTag_pre emptyTag_pre, ArrayList<CMisc> cMisc, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<EmptyTag_pre>("emptyTag_pre", emptyTag_pre),
      new PropertyZeroOrMore<CMisc>("cMisc", cMisc)
    }, firstToken, lastToken);
  }
  public Element_pre1(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public ASTNode deepCopy() {
    return new Element_pre1(cloneProperties(),firstToken,lastToken);
  }
  public EmptyTag_pre getEmptyTag_pre() {
    return ((PropertyOne<EmptyTag_pre>)getProperty("emptyTag_pre")).getValue();
  }
  public ArrayList<CMisc> getCMisc() {
    return ((PropertyZeroOrMore<CMisc>)getProperty("cMisc")).getValue();
  }
}
