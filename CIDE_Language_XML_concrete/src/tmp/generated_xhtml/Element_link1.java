package tmp.generated_xhtml;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Element_link1 extends Element_link {
  public Element_link1(EmptyTag_link emptyTag_link, ArrayList<CMisc> cMisc, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<EmptyTag_link>("emptyTag_link", emptyTag_link),
      new PropertyZeroOrMore<CMisc>("cMisc", cMisc)
    }, firstToken, lastToken);
  }
  public Element_link1(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public ASTNode deepCopy() {
    return new Element_link1(cloneProperties(),firstToken,lastToken);
  }
  public EmptyTag_link getEmptyTag_link() {
    return ((PropertyOne<EmptyTag_link>)getProperty("emptyTag_link")).getValue();
  }
  public ArrayList<CMisc> getCMisc() {
    return ((PropertyZeroOrMore<CMisc>)getProperty("cMisc")).getValue();
  }
}
