package tmp.generated_xhtml;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Element_img2 extends Element_img {
  public Element_img2(STag_img sTag_img, ArrayList<CMisc> cMisc1, ETag_img eTag_img, ArrayList<CMisc> cMisc2, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<STag_img>("sTag_img", sTag_img),
      new PropertyZeroOrMore<CMisc>("cMisc1", cMisc1),
      new PropertyOne<ETag_img>("eTag_img", eTag_img),
      new PropertyZeroOrMore<CMisc>("cMisc2", cMisc2)
    }, firstToken, lastToken);
  }
  public Element_img2(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public ASTNode deepCopy() {
    return new Element_img2(cloneProperties(),firstToken,lastToken);
  }
  public STag_img getSTag_img() {
    return ((PropertyOne<STag_img>)getProperty("sTag_img")).getValue();
  }
  public ArrayList<CMisc> getCMisc1() {
    return ((PropertyZeroOrMore<CMisc>)getProperty("cMisc1")).getValue();
  }
  public ETag_img getETag_img() {
    return ((PropertyOne<ETag_img>)getProperty("eTag_img")).getValue();
  }
  public ArrayList<CMisc> getCMisc2() {
    return ((PropertyZeroOrMore<CMisc>)getProperty("cMisc2")).getValue();
  }
}
