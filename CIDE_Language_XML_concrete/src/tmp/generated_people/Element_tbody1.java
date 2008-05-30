package tmp.generated_people;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Element_tbody1 extends Element_tbody {
  public Element_tbody1(EmptyTag_tbody emptyTag_tbody, ArrayList<CMisc> cMisc, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<EmptyTag_tbody>("emptyTag_tbody", emptyTag_tbody),
      new PropertyZeroOrMore<CMisc>("cMisc", cMisc)
    }, firstToken, lastToken);
  }
  public Element_tbody1(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public ASTNode deepCopy() {
    return new Element_tbody1(cloneProperties(),firstToken,lastToken);
  }
  public EmptyTag_tbody getEmptyTag_tbody() {
    return ((PropertyOne<EmptyTag_tbody>)getProperty("emptyTag_tbody")).getValue();
  }
  public ArrayList<CMisc> getCMisc() {
    return ((PropertyZeroOrMore<CMisc>)getProperty("cMisc")).getValue();
  }
}
