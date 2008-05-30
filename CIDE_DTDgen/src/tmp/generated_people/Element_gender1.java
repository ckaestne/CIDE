package tmp.generated_people;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Element_gender1 extends Element_gender {
  public Element_gender1(EmptyTag_gender emptyTag_gender, ArrayList<CMisc> cMisc, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<EmptyTag_gender>("emptyTag_gender", emptyTag_gender),
      new PropertyZeroOrMore<CMisc>("cMisc", cMisc)
    }, firstToken, lastToken);
  }
  public Element_gender1(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public ASTNode deepCopy() {
    return new Element_gender1(cloneProperties(),firstToken,lastToken);
  }
  public EmptyTag_gender getEmptyTag_gender() {
    return ((PropertyOne<EmptyTag_gender>)getProperty("emptyTag_gender")).getValue();
  }
  public ArrayList<CMisc> getCMisc() {
    return ((PropertyZeroOrMore<CMisc>)getProperty("cMisc")).getValue();
  }
}
