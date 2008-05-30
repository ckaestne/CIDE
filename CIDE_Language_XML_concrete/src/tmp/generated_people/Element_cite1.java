package tmp.generated_people;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Element_cite1 extends Element_cite {
  public Element_cite1(EmptyTag_cite emptyTag_cite, ArrayList<CMisc> cMisc, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<EmptyTag_cite>("emptyTag_cite", emptyTag_cite),
      new PropertyZeroOrMore<CMisc>("cMisc", cMisc)
    }, firstToken, lastToken);
  }
  public Element_cite1(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public ASTNode deepCopy() {
    return new Element_cite1(cloneProperties(),firstToken,lastToken);
  }
  public EmptyTag_cite getEmptyTag_cite() {
    return ((PropertyOne<EmptyTag_cite>)getProperty("emptyTag_cite")).getValue();
  }
  public ArrayList<CMisc> getCMisc() {
    return ((PropertyZeroOrMore<CMisc>)getProperty("cMisc")).getValue();
  }
}
