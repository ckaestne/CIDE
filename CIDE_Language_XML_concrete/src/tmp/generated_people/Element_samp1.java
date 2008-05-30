package tmp.generated_people;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Element_samp1 extends Element_samp {
  public Element_samp1(EmptyTag_samp emptyTag_samp, ArrayList<CMisc> cMisc, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<EmptyTag_samp>("emptyTag_samp", emptyTag_samp),
      new PropertyZeroOrMore<CMisc>("cMisc", cMisc)
    }, firstToken, lastToken);
  }
  public Element_samp1(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public ASTNode deepCopy() {
    return new Element_samp1(cloneProperties(),firstToken,lastToken);
  }
  public EmptyTag_samp getEmptyTag_samp() {
    return ((PropertyOne<EmptyTag_samp>)getProperty("emptyTag_samp")).getValue();
  }
  public ArrayList<CMisc> getCMisc() {
    return ((PropertyZeroOrMore<CMisc>)getProperty("cMisc")).getValue();
  }
}
