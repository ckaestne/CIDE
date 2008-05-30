package tmp.generated_people;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Element_strong1 extends Element_strong {
  public Element_strong1(EmptyTag_strong emptyTag_strong, ArrayList<CMisc> cMisc, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<EmptyTag_strong>("emptyTag_strong", emptyTag_strong),
      new PropertyZeroOrMore<CMisc>("cMisc", cMisc)
    }, firstToken, lastToken);
  }
  public Element_strong1(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public ASTNode deepCopy() {
    return new Element_strong1(cloneProperties(),firstToken,lastToken);
  }
  public EmptyTag_strong getEmptyTag_strong() {
    return ((PropertyOne<EmptyTag_strong>)getProperty("emptyTag_strong")).getValue();
  }
  public ArrayList<CMisc> getCMisc() {
    return ((PropertyZeroOrMore<CMisc>)getProperty("cMisc")).getValue();
  }
}
