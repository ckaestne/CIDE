package tmp.generated_xhtml;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Element_h41 extends Element_h4 {
  public Element_h41(EmptyTag_h4 emptyTag_h4, ArrayList<CMisc> cMisc, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<EmptyTag_h4>("emptyTag_h4", emptyTag_h4),
      new PropertyZeroOrMore<CMisc>("cMisc", cMisc)
    }, firstToken, lastToken);
  }
  public Element_h41(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public ASTNode deepCopy() {
    return new Element_h41(cloneProperties(),firstToken,lastToken);
  }
  public EmptyTag_h4 getEmptyTag_h4() {
    return ((PropertyOne<EmptyTag_h4>)getProperty("emptyTag_h4")).getValue();
  }
  public ArrayList<CMisc> getCMisc() {
    return ((PropertyZeroOrMore<CMisc>)getProperty("cMisc")).getValue();
  }
}
