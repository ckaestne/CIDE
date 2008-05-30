package tmp.generated_people;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Element_legend1 extends Element_legend {
  public Element_legend1(EmptyTag_legend emptyTag_legend, ArrayList<CMisc> cMisc, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<EmptyTag_legend>("emptyTag_legend", emptyTag_legend),
      new PropertyZeroOrMore<CMisc>("cMisc", cMisc)
    }, firstToken, lastToken);
  }
  public Element_legend1(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public ASTNode deepCopy() {
    return new Element_legend1(cloneProperties(),firstToken,lastToken);
  }
  public EmptyTag_legend getEmptyTag_legend() {
    return ((PropertyOne<EmptyTag_legend>)getProperty("emptyTag_legend")).getValue();
  }
  public ArrayList<CMisc> getCMisc() {
    return ((PropertyZeroOrMore<CMisc>)getProperty("cMisc")).getValue();
  }
}
