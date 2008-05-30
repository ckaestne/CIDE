package tmp.generated_people;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Element_col2 extends Element_col {
  public Element_col2(STag_col sTag_col, ArrayList<CMisc> cMisc1, ETag_col eTag_col, ArrayList<CMisc> cMisc2, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<STag_col>("sTag_col", sTag_col),
      new PropertyZeroOrMore<CMisc>("cMisc1", cMisc1),
      new PropertyOne<ETag_col>("eTag_col", eTag_col),
      new PropertyZeroOrMore<CMisc>("cMisc2", cMisc2)
    }, firstToken, lastToken);
  }
  public Element_col2(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public ASTNode deepCopy() {
    return new Element_col2(cloneProperties(),firstToken,lastToken);
  }
  public STag_col getSTag_col() {
    return ((PropertyOne<STag_col>)getProperty("sTag_col")).getValue();
  }
  public ArrayList<CMisc> getCMisc1() {
    return ((PropertyZeroOrMore<CMisc>)getProperty("cMisc1")).getValue();
  }
  public ETag_col getETag_col() {
    return ((PropertyOne<ETag_col>)getProperty("eTag_col")).getValue();
  }
  public ArrayList<CMisc> getCMisc2() {
    return ((PropertyZeroOrMore<CMisc>)getProperty("cMisc2")).getValue();
  }
}
