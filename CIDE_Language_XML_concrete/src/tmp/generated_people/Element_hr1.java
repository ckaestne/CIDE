package tmp.generated_people;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Element_hr1 extends Element_hr {
  public Element_hr1(EmptyTag_hr emptyTag_hr, ArrayList<CMisc> cMisc, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<EmptyTag_hr>("emptyTag_hr", emptyTag_hr),
      new PropertyZeroOrMore<CMisc>("cMisc", cMisc)
    }, firstToken, lastToken);
  }
  public Element_hr1(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public ASTNode deepCopy() {
    return new Element_hr1(cloneProperties(),firstToken,lastToken);
  }
  public EmptyTag_hr getEmptyTag_hr() {
    return ((PropertyOne<EmptyTag_hr>)getProperty("emptyTag_hr")).getValue();
  }
  public ArrayList<CMisc> getCMisc() {
    return ((PropertyZeroOrMore<CMisc>)getProperty("cMisc")).getValue();
  }
}
