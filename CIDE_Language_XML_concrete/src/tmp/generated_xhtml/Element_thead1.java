package tmp.generated_xhtml;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Element_thead1 extends Element_thead {
  public Element_thead1(EmptyTag_thead emptyTag_thead, ArrayList<CMisc> cMisc, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<EmptyTag_thead>("emptyTag_thead", emptyTag_thead),
      new PropertyZeroOrMore<CMisc>("cMisc", cMisc)
    }, firstToken, lastToken);
  }
  public Element_thead1(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public ASTNode deepCopy() {
    return new Element_thead1(cloneProperties(),firstToken,lastToken);
  }
  public EmptyTag_thead getEmptyTag_thead() {
    return ((PropertyOne<EmptyTag_thead>)getProperty("emptyTag_thead")).getValue();
  }
  public ArrayList<CMisc> getCMisc() {
    return ((PropertyZeroOrMore<CMisc>)getProperty("cMisc")).getValue();
  }
}
