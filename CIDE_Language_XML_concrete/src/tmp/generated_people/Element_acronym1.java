package tmp.generated_people;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Element_acronym1 extends Element_acronym {
  public Element_acronym1(EmptyTag_acronym emptyTag_acronym, ArrayList<CMisc> cMisc, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<EmptyTag_acronym>("emptyTag_acronym", emptyTag_acronym),
      new PropertyZeroOrMore<CMisc>("cMisc", cMisc)
    }, firstToken, lastToken);
  }
  public Element_acronym1(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public ASTNode deepCopy() {
    return new Element_acronym1(cloneProperties(),firstToken,lastToken);
  }
  public EmptyTag_acronym getEmptyTag_acronym() {
    return ((PropertyOne<EmptyTag_acronym>)getProperty("emptyTag_acronym")).getValue();
  }
  public ArrayList<CMisc> getCMisc() {
    return ((PropertyZeroOrMore<CMisc>)getProperty("cMisc")).getValue();
  }
}
