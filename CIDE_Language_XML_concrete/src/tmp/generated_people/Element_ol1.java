package tmp.generated_people;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Element_ol1 extends Element_ol {
  public Element_ol1(EmptyTag_ol emptyTag_ol, ArrayList<CMisc> cMisc, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<EmptyTag_ol>("emptyTag_ol", emptyTag_ol),
      new PropertyZeroOrMore<CMisc>("cMisc", cMisc)
    }, firstToken, lastToken);
  }
  public Element_ol1(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public ASTNode deepCopy() {
    return new Element_ol1(cloneProperties(),firstToken,lastToken);
  }
  public EmptyTag_ol getEmptyTag_ol() {
    return ((PropertyOne<EmptyTag_ol>)getProperty("emptyTag_ol")).getValue();
  }
  public ArrayList<CMisc> getCMisc() {
    return ((PropertyZeroOrMore<CMisc>)getProperty("cMisc")).getValue();
  }
}
