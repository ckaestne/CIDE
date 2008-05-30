package tmp.generated_xhtml;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Element_h61 extends Element_h6 {
  public Element_h61(EmptyTag_h6 emptyTag_h6, ArrayList<CMisc> cMisc, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<EmptyTag_h6>("emptyTag_h6", emptyTag_h6),
      new PropertyZeroOrMore<CMisc>("cMisc", cMisc)
    }, firstToken, lastToken);
  }
  public Element_h61(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public ASTNode deepCopy() {
    return new Element_h61(cloneProperties(),firstToken,lastToken);
  }
  public EmptyTag_h6 getEmptyTag_h6() {
    return ((PropertyOne<EmptyTag_h6>)getProperty("emptyTag_h6")).getValue();
  }
  public ArrayList<CMisc> getCMisc() {
    return ((PropertyZeroOrMore<CMisc>)getProperty("cMisc")).getValue();
  }
}
