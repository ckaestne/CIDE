package tmp.generated_people;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Element_birthdate1 extends Element_birthdate {
  public Element_birthdate1(EmptyTag_birthdate emptyTag_birthdate, ArrayList<CMisc> cMisc, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<EmptyTag_birthdate>("emptyTag_birthdate", emptyTag_birthdate),
      new PropertyZeroOrMore<CMisc>("cMisc", cMisc)
    }, firstToken, lastToken);
  }
  public Element_birthdate1(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public ASTNode deepCopy() {
    return new Element_birthdate1(cloneProperties(),firstToken,lastToken);
  }
  public EmptyTag_birthdate getEmptyTag_birthdate() {
    return ((PropertyOne<EmptyTag_birthdate>)getProperty("emptyTag_birthdate")).getValue();
  }
  public ArrayList<CMisc> getCMisc() {
    return ((PropertyZeroOrMore<CMisc>)getProperty("cMisc")).getValue();
  }
}
