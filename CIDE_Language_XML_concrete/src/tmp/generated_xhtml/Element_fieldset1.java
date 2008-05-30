package tmp.generated_xhtml;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Element_fieldset1 extends Element_fieldset {
  public Element_fieldset1(EmptyTag_fieldset emptyTag_fieldset, ArrayList<CMisc> cMisc, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<EmptyTag_fieldset>("emptyTag_fieldset", emptyTag_fieldset),
      new PropertyZeroOrMore<CMisc>("cMisc", cMisc)
    }, firstToken, lastToken);
  }
  public Element_fieldset1(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public ASTNode deepCopy() {
    return new Element_fieldset1(cloneProperties(),firstToken,lastToken);
  }
  public EmptyTag_fieldset getEmptyTag_fieldset() {
    return ((PropertyOne<EmptyTag_fieldset>)getProperty("emptyTag_fieldset")).getValue();
  }
  public ArrayList<CMisc> getCMisc() {
    return ((PropertyZeroOrMore<CMisc>)getProperty("cMisc")).getValue();
  }
}
