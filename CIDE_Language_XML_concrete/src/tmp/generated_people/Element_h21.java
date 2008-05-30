package tmp.generated_people;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Element_h21 extends Element_h2 {
  public Element_h21(EmptyTag_h2 emptyTag_h2, ArrayList<CMisc> cMisc, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<EmptyTag_h2>("emptyTag_h2", emptyTag_h2),
      new PropertyZeroOrMore<CMisc>("cMisc", cMisc)
    }, firstToken, lastToken);
  }
  public Element_h21(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public ASTNode deepCopy() {
    return new Element_h21(cloneProperties(),firstToken,lastToken);
  }
  public EmptyTag_h2 getEmptyTag_h2() {
    return ((PropertyOne<EmptyTag_h2>)getProperty("emptyTag_h2")).getValue();
  }
  public ArrayList<CMisc> getCMisc() {
    return ((PropertyZeroOrMore<CMisc>)getProperty("cMisc")).getValue();
  }
}
