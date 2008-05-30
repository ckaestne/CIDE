package tmp.generated_people;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Element_meta1 extends Element_meta {
  public Element_meta1(EmptyTag_meta emptyTag_meta, ArrayList<CMisc> cMisc, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<EmptyTag_meta>("emptyTag_meta", emptyTag_meta),
      new PropertyZeroOrMore<CMisc>("cMisc", cMisc)
    }, firstToken, lastToken);
  }
  public Element_meta1(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public ASTNode deepCopy() {
    return new Element_meta1(cloneProperties(),firstToken,lastToken);
  }
  public EmptyTag_meta getEmptyTag_meta() {
    return ((PropertyOne<EmptyTag_meta>)getProperty("emptyTag_meta")).getValue();
  }
  public ArrayList<CMisc> getCMisc() {
    return ((PropertyZeroOrMore<CMisc>)getProperty("cMisc")).getValue();
  }
}
