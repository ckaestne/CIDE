package tmp.generated_xhtml;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Element_dl1 extends Element_dl {
  public Element_dl1(EmptyTag_dl emptyTag_dl, ArrayList<CMisc> cMisc, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<EmptyTag_dl>("emptyTag_dl", emptyTag_dl),
      new PropertyZeroOrMore<CMisc>("cMisc", cMisc)
    }, firstToken, lastToken);
  }
  public Element_dl1(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public ASTNode deepCopy() {
    return new Element_dl1(cloneProperties(),firstToken,lastToken);
  }
  public EmptyTag_dl getEmptyTag_dl() {
    return ((PropertyOne<EmptyTag_dl>)getProperty("emptyTag_dl")).getValue();
  }
  public ArrayList<CMisc> getCMisc() {
    return ((PropertyZeroOrMore<CMisc>)getProperty("cMisc")).getValue();
  }
}
