package tmp.generated_people;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Element_ul1 extends Element_ul {
  public Element_ul1(EmptyTag_ul emptyTag_ul, ArrayList<CMisc> cMisc, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<EmptyTag_ul>("emptyTag_ul", emptyTag_ul),
      new PropertyZeroOrMore<CMisc>("cMisc", cMisc)
    }, firstToken, lastToken);
  }
  public Element_ul1(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public ASTNode deepCopy() {
    return new Element_ul1(cloneProperties(),firstToken,lastToken);
  }
  public EmptyTag_ul getEmptyTag_ul() {
    return ((PropertyOne<EmptyTag_ul>)getProperty("emptyTag_ul")).getValue();
  }
  public ArrayList<CMisc> getCMisc() {
    return ((PropertyZeroOrMore<CMisc>)getProperty("cMisc")).getValue();
  }
}
