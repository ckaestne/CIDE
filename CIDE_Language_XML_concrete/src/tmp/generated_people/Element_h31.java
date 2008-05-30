package tmp.generated_people;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Element_h31 extends Element_h3 {
  public Element_h31(EmptyTag_h3 emptyTag_h3, ArrayList<CMisc> cMisc, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<EmptyTag_h3>("emptyTag_h3", emptyTag_h3),
      new PropertyZeroOrMore<CMisc>("cMisc", cMisc)
    }, firstToken, lastToken);
  }
  public Element_h31(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public ASTNode deepCopy() {
    return new Element_h31(cloneProperties(),firstToken,lastToken);
  }
  public EmptyTag_h3 getEmptyTag_h3() {
    return ((PropertyOne<EmptyTag_h3>)getProperty("emptyTag_h3")).getValue();
  }
  public ArrayList<CMisc> getCMisc() {
    return ((PropertyZeroOrMore<CMisc>)getProperty("cMisc")).getValue();
  }
}
