package tmp.generated_people;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Element_h51 extends Element_h5 {
  public Element_h51(EmptyTag_h5 emptyTag_h5, ArrayList<CMisc> cMisc, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<EmptyTag_h5>("emptyTag_h5", emptyTag_h5),
      new PropertyZeroOrMore<CMisc>("cMisc", cMisc)
    }, firstToken, lastToken);
  }
  public Element_h51(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public ASTNode deepCopy() {
    return new Element_h51(cloneProperties(),firstToken,lastToken);
  }
  public EmptyTag_h5 getEmptyTag_h5() {
    return ((PropertyOne<EmptyTag_h5>)getProperty("emptyTag_h5")).getValue();
  }
  public ArrayList<CMisc> getCMisc() {
    return ((PropertyZeroOrMore<CMisc>)getProperty("cMisc")).getValue();
  }
}
