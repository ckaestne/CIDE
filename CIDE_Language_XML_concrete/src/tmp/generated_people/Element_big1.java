package tmp.generated_people;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Element_big1 extends Element_big {
  public Element_big1(EmptyTag_big emptyTag_big, ArrayList<CMisc> cMisc, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<EmptyTag_big>("emptyTag_big", emptyTag_big),
      new PropertyZeroOrMore<CMisc>("cMisc", cMisc)
    }, firstToken, lastToken);
  }
  public Element_big1(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public ASTNode deepCopy() {
    return new Element_big1(cloneProperties(),firstToken,lastToken);
  }
  public EmptyTag_big getEmptyTag_big() {
    return ((PropertyOne<EmptyTag_big>)getProperty("emptyTag_big")).getValue();
  }
  public ArrayList<CMisc> getCMisc() {
    return ((PropertyZeroOrMore<CMisc>)getProperty("cMisc")).getValue();
  }
}
