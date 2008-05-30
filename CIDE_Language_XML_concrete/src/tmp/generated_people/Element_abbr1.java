package tmp.generated_people;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Element_abbr1 extends Element_abbr {
  public Element_abbr1(EmptyTag_abbr emptyTag_abbr, ArrayList<CMisc> cMisc, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<EmptyTag_abbr>("emptyTag_abbr", emptyTag_abbr),
      new PropertyZeroOrMore<CMisc>("cMisc", cMisc)
    }, firstToken, lastToken);
  }
  public Element_abbr1(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public ASTNode deepCopy() {
    return new Element_abbr1(cloneProperties(),firstToken,lastToken);
  }
  public EmptyTag_abbr getEmptyTag_abbr() {
    return ((PropertyOne<EmptyTag_abbr>)getProperty("emptyTag_abbr")).getValue();
  }
  public ArrayList<CMisc> getCMisc() {
    return ((PropertyZeroOrMore<CMisc>)getProperty("cMisc")).getValue();
  }
}
