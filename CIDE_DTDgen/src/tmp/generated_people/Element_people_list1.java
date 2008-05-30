package tmp.generated_people;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Element_people_list1 extends Element_people_list {
  public Element_people_list1(EmptyTag_people_list emptyTag_people_list, ArrayList<CMisc> cMisc, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<EmptyTag_people_list>("emptyTag_people_list", emptyTag_people_list),
      new PropertyZeroOrMore<CMisc>("cMisc", cMisc)
    }, firstToken, lastToken);
  }
  public Element_people_list1(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public ASTNode deepCopy() {
    return new Element_people_list1(cloneProperties(),firstToken,lastToken);
  }
  public EmptyTag_people_list getEmptyTag_people_list() {
    return ((PropertyOne<EmptyTag_people_list>)getProperty("emptyTag_people_list")).getValue();
  }
  public ArrayList<CMisc> getCMisc() {
    return ((PropertyZeroOrMore<CMisc>)getProperty("cMisc")).getValue();
  }
}
