package tmp.generated_xhtml;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Element_kbd1 extends Element_kbd {
  public Element_kbd1(EmptyTag_kbd emptyTag_kbd, ArrayList<CMisc> cMisc, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<EmptyTag_kbd>("emptyTag_kbd", emptyTag_kbd),
      new PropertyZeroOrMore<CMisc>("cMisc", cMisc)
    }, firstToken, lastToken);
  }
  public Element_kbd1(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public ASTNode deepCopy() {
    return new Element_kbd1(cloneProperties(),firstToken,lastToken);
  }
  public EmptyTag_kbd getEmptyTag_kbd() {
    return ((PropertyOne<EmptyTag_kbd>)getProperty("emptyTag_kbd")).getValue();
  }
  public ArrayList<CMisc> getCMisc() {
    return ((PropertyZeroOrMore<CMisc>)getProperty("cMisc")).getValue();
  }
}
