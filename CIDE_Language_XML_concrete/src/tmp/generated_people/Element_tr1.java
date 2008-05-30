package tmp.generated_people;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Element_tr1 extends Element_tr {
  public Element_tr1(EmptyTag_tr emptyTag_tr, ArrayList<CMisc> cMisc, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<EmptyTag_tr>("emptyTag_tr", emptyTag_tr),
      new PropertyZeroOrMore<CMisc>("cMisc", cMisc)
    }, firstToken, lastToken);
  }
  public Element_tr1(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public ASTNode deepCopy() {
    return new Element_tr1(cloneProperties(),firstToken,lastToken);
  }
  public EmptyTag_tr getEmptyTag_tr() {
    return ((PropertyOne<EmptyTag_tr>)getProperty("emptyTag_tr")).getValue();
  }
  public ArrayList<CMisc> getCMisc() {
    return ((PropertyZeroOrMore<CMisc>)getProperty("cMisc")).getValue();
  }
}
