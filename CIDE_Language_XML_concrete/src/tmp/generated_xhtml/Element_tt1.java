package tmp.generated_xhtml;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Element_tt1 extends Element_tt {
  public Element_tt1(EmptyTag_tt emptyTag_tt, ArrayList<CMisc> cMisc, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<EmptyTag_tt>("emptyTag_tt", emptyTag_tt),
      new PropertyZeroOrMore<CMisc>("cMisc", cMisc)
    }, firstToken, lastToken);
  }
  public Element_tt1(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public ASTNode deepCopy() {
    return new Element_tt1(cloneProperties(),firstToken,lastToken);
  }
  public EmptyTag_tt getEmptyTag_tt() {
    return ((PropertyOne<EmptyTag_tt>)getProperty("emptyTag_tt")).getValue();
  }
  public ArrayList<CMisc> getCMisc() {
    return ((PropertyZeroOrMore<CMisc>)getProperty("cMisc")).getValue();
  }
}
