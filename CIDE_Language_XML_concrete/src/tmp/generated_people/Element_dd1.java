package tmp.generated_people;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Element_dd1 extends Element_dd {
  public Element_dd1(EmptyTag_dd emptyTag_dd, ArrayList<CMisc> cMisc, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<EmptyTag_dd>("emptyTag_dd", emptyTag_dd),
      new PropertyZeroOrMore<CMisc>("cMisc", cMisc)
    }, firstToken, lastToken);
  }
  public Element_dd1(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public ASTNode deepCopy() {
    return new Element_dd1(cloneProperties(),firstToken,lastToken);
  }
  public EmptyTag_dd getEmptyTag_dd() {
    return ((PropertyOne<EmptyTag_dd>)getProperty("emptyTag_dd")).getValue();
  }
  public ArrayList<CMisc> getCMisc() {
    return ((PropertyZeroOrMore<CMisc>)getProperty("cMisc")).getValue();
  }
}
