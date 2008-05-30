package tmp.generated_xhtml;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Element_optgroup1 extends Element_optgroup {
  public Element_optgroup1(EmptyTag_optgroup emptyTag_optgroup, ArrayList<CMisc> cMisc, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<EmptyTag_optgroup>("emptyTag_optgroup", emptyTag_optgroup),
      new PropertyZeroOrMore<CMisc>("cMisc", cMisc)
    }, firstToken, lastToken);
  }
  public Element_optgroup1(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public ASTNode deepCopy() {
    return new Element_optgroup1(cloneProperties(),firstToken,lastToken);
  }
  public EmptyTag_optgroup getEmptyTag_optgroup() {
    return ((PropertyOne<EmptyTag_optgroup>)getProperty("emptyTag_optgroup")).getValue();
  }
  public ArrayList<CMisc> getCMisc() {
    return ((PropertyZeroOrMore<CMisc>)getProperty("cMisc")).getValue();
  }
}
