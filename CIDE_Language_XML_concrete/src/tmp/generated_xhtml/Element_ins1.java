package tmp.generated_xhtml;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Element_ins1 extends Element_ins {
  public Element_ins1(EmptyTag_ins emptyTag_ins, ArrayList<CMisc> cMisc, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<EmptyTag_ins>("emptyTag_ins", emptyTag_ins),
      new PropertyZeroOrMore<CMisc>("cMisc", cMisc)
    }, firstToken, lastToken);
  }
  public Element_ins1(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public ASTNode deepCopy() {
    return new Element_ins1(cloneProperties(),firstToken,lastToken);
  }
  public EmptyTag_ins getEmptyTag_ins() {
    return ((PropertyOne<EmptyTag_ins>)getProperty("emptyTag_ins")).getValue();
  }
  public ArrayList<CMisc> getCMisc() {
    return ((PropertyZeroOrMore<CMisc>)getProperty("cMisc")).getValue();
  }
}
