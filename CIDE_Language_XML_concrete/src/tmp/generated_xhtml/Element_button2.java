package tmp.generated_xhtml;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Element_button2 extends Element_button {
  public Element_button2(STag_button sTag_button, ArrayList<CMisc> cMisc1, ArrayList<Content_button_Choice1> content_button_Choice1, ETag_button eTag_button, ArrayList<CMisc> cMisc2, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<STag_button>("sTag_button", sTag_button),
      new PropertyZeroOrMore<CMisc>("cMisc1", cMisc1),
      new PropertyZeroOrMore<Content_button_Choice1>("content_button_Choice1", content_button_Choice1),
      new PropertyOne<ETag_button>("eTag_button", eTag_button),
      new PropertyZeroOrMore<CMisc>("cMisc2", cMisc2)
    }, firstToken, lastToken);
  }
  public Element_button2(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public ASTNode deepCopy() {
    return new Element_button2(cloneProperties(),firstToken,lastToken);
  }
  public STag_button getSTag_button() {
    return ((PropertyOne<STag_button>)getProperty("sTag_button")).getValue();
  }
  public ArrayList<CMisc> getCMisc1() {
    return ((PropertyZeroOrMore<CMisc>)getProperty("cMisc1")).getValue();
  }
  public ArrayList<Content_button_Choice1> getContent_button_Choice1() {
    return ((PropertyZeroOrMore<Content_button_Choice1>)getProperty("content_button_Choice1")).getValue();
  }
  public ETag_button getETag_button() {
    return ((PropertyOne<ETag_button>)getProperty("eTag_button")).getValue();
  }
  public ArrayList<CMisc> getCMisc2() {
    return ((PropertyZeroOrMore<CMisc>)getProperty("cMisc2")).getValue();
  }
}
