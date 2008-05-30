package tmp.generated_xhtml;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Element_dt2 extends Element_dt {
  public Element_dt2(STag_dt sTag_dt, ArrayList<CMisc> cMisc1, ArrayList<Content_dt_Choice1> content_dt_Choice1, ETag_dt eTag_dt, ArrayList<CMisc> cMisc2, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<STag_dt>("sTag_dt", sTag_dt),
      new PropertyZeroOrMore<CMisc>("cMisc1", cMisc1),
      new PropertyZeroOrMore<Content_dt_Choice1>("content_dt_Choice1", content_dt_Choice1),
      new PropertyOne<ETag_dt>("eTag_dt", eTag_dt),
      new PropertyZeroOrMore<CMisc>("cMisc2", cMisc2)
    }, firstToken, lastToken);
  }
  public Element_dt2(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public ASTNode deepCopy() {
    return new Element_dt2(cloneProperties(),firstToken,lastToken);
  }
  public STag_dt getSTag_dt() {
    return ((PropertyOne<STag_dt>)getProperty("sTag_dt")).getValue();
  }
  public ArrayList<CMisc> getCMisc1() {
    return ((PropertyZeroOrMore<CMisc>)getProperty("cMisc1")).getValue();
  }
  public ArrayList<Content_dt_Choice1> getContent_dt_Choice1() {
    return ((PropertyZeroOrMore<Content_dt_Choice1>)getProperty("content_dt_Choice1")).getValue();
  }
  public ETag_dt getETag_dt() {
    return ((PropertyOne<ETag_dt>)getProperty("eTag_dt")).getValue();
  }
  public ArrayList<CMisc> getCMisc2() {
    return ((PropertyZeroOrMore<CMisc>)getProperty("cMisc2")).getValue();
  }
}
