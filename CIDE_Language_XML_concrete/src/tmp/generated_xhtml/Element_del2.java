package tmp.generated_xhtml;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Element_del2 extends Element_del {
  public Element_del2(STag_del sTag_del, ArrayList<CMisc> cMisc1, ArrayList<Content_del_Choice1> content_del_Choice1, ETag_del eTag_del, ArrayList<CMisc> cMisc2, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<STag_del>("sTag_del", sTag_del),
      new PropertyZeroOrMore<CMisc>("cMisc1", cMisc1),
      new PropertyZeroOrMore<Content_del_Choice1>("content_del_Choice1", content_del_Choice1),
      new PropertyOne<ETag_del>("eTag_del", eTag_del),
      new PropertyZeroOrMore<CMisc>("cMisc2", cMisc2)
    }, firstToken, lastToken);
  }
  public Element_del2(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public ASTNode deepCopy() {
    return new Element_del2(cloneProperties(),firstToken,lastToken);
  }
  public STag_del getSTag_del() {
    return ((PropertyOne<STag_del>)getProperty("sTag_del")).getValue();
  }
  public ArrayList<CMisc> getCMisc1() {
    return ((PropertyZeroOrMore<CMisc>)getProperty("cMisc1")).getValue();
  }
  public ArrayList<Content_del_Choice1> getContent_del_Choice1() {
    return ((PropertyZeroOrMore<Content_del_Choice1>)getProperty("content_del_Choice1")).getValue();
  }
  public ETag_del getETag_del() {
    return ((PropertyOne<ETag_del>)getProperty("eTag_del")).getValue();
  }
  public ArrayList<CMisc> getCMisc2() {
    return ((PropertyZeroOrMore<CMisc>)getProperty("cMisc2")).getValue();
  }
}
