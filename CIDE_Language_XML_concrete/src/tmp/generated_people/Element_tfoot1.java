package tmp.generated_people;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Element_tfoot1 extends Element_tfoot {
  public Element_tfoot1(EmptyTag_tfoot emptyTag_tfoot, ArrayList<CMisc> cMisc, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<EmptyTag_tfoot>("emptyTag_tfoot", emptyTag_tfoot),
      new PropertyZeroOrMore<CMisc>("cMisc", cMisc)
    }, firstToken, lastToken);
  }
  public Element_tfoot1(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public ASTNode deepCopy() {
    return new Element_tfoot1(cloneProperties(),firstToken,lastToken);
  }
  public EmptyTag_tfoot getEmptyTag_tfoot() {
    return ((PropertyOne<EmptyTag_tfoot>)getProperty("emptyTag_tfoot")).getValue();
  }
  public ArrayList<CMisc> getCMisc() {
    return ((PropertyZeroOrMore<CMisc>)getProperty("cMisc")).getValue();
  }
}
