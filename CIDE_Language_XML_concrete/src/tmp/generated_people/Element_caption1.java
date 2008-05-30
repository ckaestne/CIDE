package tmp.generated_people;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Element_caption1 extends Element_caption {
  public Element_caption1(EmptyTag_caption emptyTag_caption, ArrayList<CMisc> cMisc, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<EmptyTag_caption>("emptyTag_caption", emptyTag_caption),
      new PropertyZeroOrMore<CMisc>("cMisc", cMisc)
    }, firstToken, lastToken);
  }
  public Element_caption1(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public ASTNode deepCopy() {
    return new Element_caption1(cloneProperties(),firstToken,lastToken);
  }
  public EmptyTag_caption getEmptyTag_caption() {
    return ((PropertyOne<EmptyTag_caption>)getProperty("emptyTag_caption")).getValue();
  }
  public ArrayList<CMisc> getCMisc() {
    return ((PropertyZeroOrMore<CMisc>)getProperty("cMisc")).getValue();
  }
}
