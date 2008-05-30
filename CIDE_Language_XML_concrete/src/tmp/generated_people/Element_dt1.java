package tmp.generated_people;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Element_dt1 extends Element_dt {
  public Element_dt1(EmptyTag_dt emptyTag_dt, ArrayList<CMisc> cMisc, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<EmptyTag_dt>("emptyTag_dt", emptyTag_dt),
      new PropertyZeroOrMore<CMisc>("cMisc", cMisc)
    }, firstToken, lastToken);
  }
  public Element_dt1(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public ASTNode deepCopy() {
    return new Element_dt1(cloneProperties(),firstToken,lastToken);
  }
  public EmptyTag_dt getEmptyTag_dt() {
    return ((PropertyOne<EmptyTag_dt>)getProperty("emptyTag_dt")).getValue();
  }
  public ArrayList<CMisc> getCMisc() {
    return ((PropertyZeroOrMore<CMisc>)getProperty("cMisc")).getValue();
  }
}
