package tmp.generated_people;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Element_socialsecuritynumber1 extends Element_socialsecuritynumber {
  public Element_socialsecuritynumber1(EmptyTag_socialsecuritynumber emptyTag_socialsecuritynumber, ArrayList<CMisc> cMisc, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<EmptyTag_socialsecuritynumber>("emptyTag_socialsecuritynumber", emptyTag_socialsecuritynumber),
      new PropertyZeroOrMore<CMisc>("cMisc", cMisc)
    }, firstToken, lastToken);
  }
  public Element_socialsecuritynumber1(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public ASTNode deepCopy() {
    return new Element_socialsecuritynumber1(cloneProperties(),firstToken,lastToken);
  }
  public EmptyTag_socialsecuritynumber getEmptyTag_socialsecuritynumber() {
    return ((PropertyOne<EmptyTag_socialsecuritynumber>)getProperty("emptyTag_socialsecuritynumber")).getValue();
  }
  public ArrayList<CMisc> getCMisc() {
    return ((PropertyZeroOrMore<CMisc>)getProperty("cMisc")).getValue();
  }
}
