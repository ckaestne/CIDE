package tmp.generated_xhtml;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Element_li2 extends Element_li {
  public Element_li2(STag_li sTag_li, ArrayList<CMisc> cMisc1, ArrayList<Content_li_Choice1> content_li_Choice1, ETag_li eTag_li, ArrayList<CMisc> cMisc2, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<STag_li>("sTag_li", sTag_li),
      new PropertyZeroOrMore<CMisc>("cMisc1", cMisc1),
      new PropertyZeroOrMore<Content_li_Choice1>("content_li_Choice1", content_li_Choice1),
      new PropertyOne<ETag_li>("eTag_li", eTag_li),
      new PropertyZeroOrMore<CMisc>("cMisc2", cMisc2)
    }, firstToken, lastToken);
  }
  public Element_li2(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public ASTNode deepCopy() {
    return new Element_li2(cloneProperties(),firstToken,lastToken);
  }
  public STag_li getSTag_li() {
    return ((PropertyOne<STag_li>)getProperty("sTag_li")).getValue();
  }
  public ArrayList<CMisc> getCMisc1() {
    return ((PropertyZeroOrMore<CMisc>)getProperty("cMisc1")).getValue();
  }
  public ArrayList<Content_li_Choice1> getContent_li_Choice1() {
    return ((PropertyZeroOrMore<Content_li_Choice1>)getProperty("content_li_Choice1")).getValue();
  }
  public ETag_li getETag_li() {
    return ((PropertyOne<ETag_li>)getProperty("eTag_li")).getValue();
  }
  public ArrayList<CMisc> getCMisc2() {
    return ((PropertyZeroOrMore<CMisc>)getProperty("cMisc2")).getValue();
  }
}
