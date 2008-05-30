package tmp.generated_people;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Element_colgroup1 extends Element_colgroup {
  public Element_colgroup1(EmptyTag_colgroup emptyTag_colgroup, ArrayList<CMisc> cMisc, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<EmptyTag_colgroup>("emptyTag_colgroup", emptyTag_colgroup),
      new PropertyZeroOrMore<CMisc>("cMisc", cMisc)
    }, firstToken, lastToken);
  }
  public Element_colgroup1(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public ASTNode deepCopy() {
    return new Element_colgroup1(cloneProperties(),firstToken,lastToken);
  }
  public EmptyTag_colgroup getEmptyTag_colgroup() {
    return ((PropertyOne<EmptyTag_colgroup>)getProperty("emptyTag_colgroup")).getValue();
  }
  public ArrayList<CMisc> getCMisc() {
    return ((PropertyZeroOrMore<CMisc>)getProperty("cMisc")).getValue();
  }
}
