package tmp.generated_xhtml;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Element_br2 extends Element_br {
  public Element_br2(STag_br sTag_br, ArrayList<CMisc> cMisc1, ETag_br eTag_br, ArrayList<CMisc> cMisc2, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<STag_br>("sTag_br", sTag_br),
      new PropertyZeroOrMore<CMisc>("cMisc1", cMisc1),
      new PropertyOne<ETag_br>("eTag_br", eTag_br),
      new PropertyZeroOrMore<CMisc>("cMisc2", cMisc2)
    }, firstToken, lastToken);
  }
  public Element_br2(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public ASTNode deepCopy() {
    return new Element_br2(cloneProperties(),firstToken,lastToken);
  }
  public STag_br getSTag_br() {
    return ((PropertyOne<STag_br>)getProperty("sTag_br")).getValue();
  }
  public ArrayList<CMisc> getCMisc1() {
    return ((PropertyZeroOrMore<CMisc>)getProperty("cMisc1")).getValue();
  }
  public ETag_br getETag_br() {
    return ((PropertyOne<ETag_br>)getProperty("eTag_br")).getValue();
  }
  public ArrayList<CMisc> getCMisc2() {
    return ((PropertyZeroOrMore<CMisc>)getProperty("cMisc2")).getValue();
  }
}
