package tmp.generated_xhtml;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Element_form2 extends Element_form {
  public Element_form2(STag_form sTag_form, ArrayList<CMisc> cMisc1, ArrayList<Content_form_Choice1> content_form_Choice1, ETag_form eTag_form, ArrayList<CMisc> cMisc2, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<STag_form>("sTag_form", sTag_form),
      new PropertyZeroOrMore<CMisc>("cMisc1", cMisc1),
      new PropertyZeroOrMore<Content_form_Choice1>("content_form_Choice1", content_form_Choice1),
      new PropertyOne<ETag_form>("eTag_form", eTag_form),
      new PropertyZeroOrMore<CMisc>("cMisc2", cMisc2)
    }, firstToken, lastToken);
  }
  public Element_form2(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public ASTNode deepCopy() {
    return new Element_form2(cloneProperties(),firstToken,lastToken);
  }
  public STag_form getSTag_form() {
    return ((PropertyOne<STag_form>)getProperty("sTag_form")).getValue();
  }
  public ArrayList<CMisc> getCMisc1() {
    return ((PropertyZeroOrMore<CMisc>)getProperty("cMisc1")).getValue();
  }
  public ArrayList<Content_form_Choice1> getContent_form_Choice1() {
    return ((PropertyZeroOrMore<Content_form_Choice1>)getProperty("content_form_Choice1")).getValue();
  }
  public ETag_form getETag_form() {
    return ((PropertyOne<ETag_form>)getProperty("eTag_form")).getValue();
  }
  public ArrayList<CMisc> getCMisc2() {
    return ((PropertyZeroOrMore<CMisc>)getProperty("cMisc2")).getValue();
  }
}
