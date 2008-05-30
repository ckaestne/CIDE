package tmp.generated_people;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Element_hr2 extends Element_hr {
  public Element_hr2(STag_hr sTag_hr, ArrayList<CMisc> cMisc1, ETag_hr eTag_hr, ArrayList<CMisc> cMisc2, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<STag_hr>("sTag_hr", sTag_hr),
      new PropertyZeroOrMore<CMisc>("cMisc1", cMisc1),
      new PropertyOne<ETag_hr>("eTag_hr", eTag_hr),
      new PropertyZeroOrMore<CMisc>("cMisc2", cMisc2)
    }, firstToken, lastToken);
  }
  public Element_hr2(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public ASTNode deepCopy() {
    return new Element_hr2(cloneProperties(),firstToken,lastToken);
  }
  public STag_hr getSTag_hr() {
    return ((PropertyOne<STag_hr>)getProperty("sTag_hr")).getValue();
  }
  public ArrayList<CMisc> getCMisc1() {
    return ((PropertyZeroOrMore<CMisc>)getProperty("cMisc1")).getValue();
  }
  public ETag_hr getETag_hr() {
    return ((PropertyOne<ETag_hr>)getProperty("eTag_hr")).getValue();
  }
  public ArrayList<CMisc> getCMisc2() {
    return ((PropertyZeroOrMore<CMisc>)getProperty("cMisc2")).getValue();
  }
}
