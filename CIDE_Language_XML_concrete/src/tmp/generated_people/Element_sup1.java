package tmp.generated_people;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Element_sup1 extends Element_sup {
  public Element_sup1(EmptyTag_sup emptyTag_sup, ArrayList<CMisc> cMisc, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<EmptyTag_sup>("emptyTag_sup", emptyTag_sup),
      new PropertyZeroOrMore<CMisc>("cMisc", cMisc)
    }, firstToken, lastToken);
  }
  public Element_sup1(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public ASTNode deepCopy() {
    return new Element_sup1(cloneProperties(),firstToken,lastToken);
  }
  public EmptyTag_sup getEmptyTag_sup() {
    return ((PropertyOne<EmptyTag_sup>)getProperty("emptyTag_sup")).getValue();
  }
  public ArrayList<CMisc> getCMisc() {
    return ((PropertyZeroOrMore<CMisc>)getProperty("cMisc")).getValue();
  }
}
