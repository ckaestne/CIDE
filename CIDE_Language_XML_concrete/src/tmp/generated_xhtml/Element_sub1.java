package tmp.generated_xhtml;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Element_sub1 extends Element_sub {
  public Element_sub1(EmptyTag_sub emptyTag_sub, ArrayList<CMisc> cMisc, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<EmptyTag_sub>("emptyTag_sub", emptyTag_sub),
      new PropertyZeroOrMore<CMisc>("cMisc", cMisc)
    }, firstToken, lastToken);
  }
  public Element_sub1(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public ASTNode deepCopy() {
    return new Element_sub1(cloneProperties(),firstToken,lastToken);
  }
  public EmptyTag_sub getEmptyTag_sub() {
    return ((PropertyOne<EmptyTag_sub>)getProperty("emptyTag_sub")).getValue();
  }
  public ArrayList<CMisc> getCMisc() {
    return ((PropertyZeroOrMore<CMisc>)getProperty("cMisc")).getValue();
  }
}
