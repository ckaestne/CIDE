package tmp.generated_xhtml;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Element_var2 extends Element_var {
  public Element_var2(STag_var sTag_var, ArrayList<CMisc> cMisc1, ArrayList<Content_var_Choice1> content_var_Choice1, ETag_var eTag_var, ArrayList<CMisc> cMisc2, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<STag_var>("sTag_var", sTag_var),
      new PropertyZeroOrMore<CMisc>("cMisc1", cMisc1),
      new PropertyZeroOrMore<Content_var_Choice1>("content_var_Choice1", content_var_Choice1),
      new PropertyOne<ETag_var>("eTag_var", eTag_var),
      new PropertyZeroOrMore<CMisc>("cMisc2", cMisc2)
    }, firstToken, lastToken);
  }
  public Element_var2(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public ASTNode deepCopy() {
    return new Element_var2(cloneProperties(),firstToken,lastToken);
  }
  public STag_var getSTag_var() {
    return ((PropertyOne<STag_var>)getProperty("sTag_var")).getValue();
  }
  public ArrayList<CMisc> getCMisc1() {
    return ((PropertyZeroOrMore<CMisc>)getProperty("cMisc1")).getValue();
  }
  public ArrayList<Content_var_Choice1> getContent_var_Choice1() {
    return ((PropertyZeroOrMore<Content_var_Choice1>)getProperty("content_var_Choice1")).getValue();
  }
  public ETag_var getETag_var() {
    return ((PropertyOne<ETag_var>)getProperty("eTag_var")).getValue();
  }
  public ArrayList<CMisc> getCMisc2() {
    return ((PropertyZeroOrMore<CMisc>)getProperty("cMisc2")).getValue();
  }
}
