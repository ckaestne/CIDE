package tmp.generated_xhtml;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Element_bdo1 extends Element_bdo {
  public Element_bdo1(EmptyTag_bdo emptyTag_bdo, ArrayList<CMisc> cMisc, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<EmptyTag_bdo>("emptyTag_bdo", emptyTag_bdo),
      new PropertyZeroOrMore<CMisc>("cMisc", cMisc)
    }, firstToken, lastToken);
  }
  public Element_bdo1(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public ASTNode deepCopy() {
    return new Element_bdo1(cloneProperties(),firstToken,lastToken);
  }
  public EmptyTag_bdo getEmptyTag_bdo() {
    return ((PropertyOne<EmptyTag_bdo>)getProperty("emptyTag_bdo")).getValue();
  }
  public ArrayList<CMisc> getCMisc() {
    return ((PropertyZeroOrMore<CMisc>)getProperty("cMisc")).getValue();
  }
}
