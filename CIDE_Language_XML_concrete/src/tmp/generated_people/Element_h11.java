package tmp.generated_people;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Element_h11 extends Element_h1 {
  public Element_h11(EmptyTag_h1 emptyTag_h1, ArrayList<CMisc> cMisc, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<EmptyTag_h1>("emptyTag_h1", emptyTag_h1),
      new PropertyZeroOrMore<CMisc>("cMisc", cMisc)
    }, firstToken, lastToken);
  }
  public Element_h11(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public ASTNode deepCopy() {
    return new Element_h11(cloneProperties(),firstToken,lastToken);
  }
  public EmptyTag_h1 getEmptyTag_h1() {
    return ((PropertyOne<EmptyTag_h1>)getProperty("emptyTag_h1")).getValue();
  }
  public ArrayList<CMisc> getCMisc() {
    return ((PropertyZeroOrMore<CMisc>)getProperty("cMisc")).getValue();
  }
}
