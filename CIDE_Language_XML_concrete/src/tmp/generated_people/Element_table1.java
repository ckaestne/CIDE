package tmp.generated_people;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Element_table1 extends Element_table {
  public Element_table1(EmptyTag_table emptyTag_table, ArrayList<CMisc> cMisc, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<EmptyTag_table>("emptyTag_table", emptyTag_table),
      new PropertyZeroOrMore<CMisc>("cMisc", cMisc)
    }, firstToken, lastToken);
  }
  public Element_table1(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public ASTNode deepCopy() {
    return new Element_table1(cloneProperties(),firstToken,lastToken);
  }
  public EmptyTag_table getEmptyTag_table() {
    return ((PropertyOne<EmptyTag_table>)getProperty("emptyTag_table")).getValue();
  }
  public ArrayList<CMisc> getCMisc() {
    return ((PropertyZeroOrMore<CMisc>)getProperty("cMisc")).getValue();
  }
}
