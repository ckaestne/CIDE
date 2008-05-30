package tmp.generated_xhtml;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Element_base2 extends Element_base {
  public Element_base2(STag_base sTag_base, ArrayList<CMisc> cMisc1, ETag_base eTag_base, ArrayList<CMisc> cMisc2, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<STag_base>("sTag_base", sTag_base),
      new PropertyZeroOrMore<CMisc>("cMisc1", cMisc1),
      new PropertyOne<ETag_base>("eTag_base", eTag_base),
      new PropertyZeroOrMore<CMisc>("cMisc2", cMisc2)
    }, firstToken, lastToken);
  }
  public Element_base2(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public ASTNode deepCopy() {
    return new Element_base2(cloneProperties(),firstToken,lastToken);
  }
  public STag_base getSTag_base() {
    return ((PropertyOne<STag_base>)getProperty("sTag_base")).getValue();
  }
  public ArrayList<CMisc> getCMisc1() {
    return ((PropertyZeroOrMore<CMisc>)getProperty("cMisc1")).getValue();
  }
  public ETag_base getETag_base() {
    return ((PropertyOne<ETag_base>)getProperty("eTag_base")).getValue();
  }
  public ArrayList<CMisc> getCMisc2() {
    return ((PropertyZeroOrMore<CMisc>)getProperty("cMisc2")).getValue();
  }
}
