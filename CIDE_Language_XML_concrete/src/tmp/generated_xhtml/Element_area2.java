package tmp.generated_xhtml;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Element_area2 extends Element_area {
  public Element_area2(STag_area sTag_area, ArrayList<CMisc> cMisc1, ETag_area eTag_area, ArrayList<CMisc> cMisc2, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<STag_area>("sTag_area", sTag_area),
      new PropertyZeroOrMore<CMisc>("cMisc1", cMisc1),
      new PropertyOne<ETag_area>("eTag_area", eTag_area),
      new PropertyZeroOrMore<CMisc>("cMisc2", cMisc2)
    }, firstToken, lastToken);
  }
  public Element_area2(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public ASTNode deepCopy() {
    return new Element_area2(cloneProperties(),firstToken,lastToken);
  }
  public STag_area getSTag_area() {
    return ((PropertyOne<STag_area>)getProperty("sTag_area")).getValue();
  }
  public ArrayList<CMisc> getCMisc1() {
    return ((PropertyZeroOrMore<CMisc>)getProperty("cMisc1")).getValue();
  }
  public ETag_area getETag_area() {
    return ((PropertyOne<ETag_area>)getProperty("eTag_area")).getValue();
  }
  public ArrayList<CMisc> getCMisc2() {
    return ((PropertyZeroOrMore<CMisc>)getProperty("cMisc2")).getValue();
  }
}
