package tmp.generated_people;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Element_textarea1 extends Element_textarea {
  public Element_textarea1(EmptyTag_textarea emptyTag_textarea, ArrayList<CMisc> cMisc, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<EmptyTag_textarea>("emptyTag_textarea", emptyTag_textarea),
      new PropertyZeroOrMore<CMisc>("cMisc", cMisc)
    }, firstToken, lastToken);
  }
  public Element_textarea1(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public ASTNode deepCopy() {
    return new Element_textarea1(cloneProperties(),firstToken,lastToken);
  }
  public EmptyTag_textarea getEmptyTag_textarea() {
    return ((PropertyOne<EmptyTag_textarea>)getProperty("emptyTag_textarea")).getValue();
  }
  public ArrayList<CMisc> getCMisc() {
    return ((PropertyZeroOrMore<CMisc>)getProperty("cMisc")).getValue();
  }
}
