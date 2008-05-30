package tmp.generated_xhtml;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Element_abbr2 extends Element_abbr {
  public Element_abbr2(STag_abbr sTag_abbr, ArrayList<CMisc> cMisc1, ArrayList<Content_abbr_Choice1> content_abbr_Choice1, ETag_abbr eTag_abbr, ArrayList<CMisc> cMisc2, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<STag_abbr>("sTag_abbr", sTag_abbr),
      new PropertyZeroOrMore<CMisc>("cMisc1", cMisc1),
      new PropertyZeroOrMore<Content_abbr_Choice1>("content_abbr_Choice1", content_abbr_Choice1),
      new PropertyOne<ETag_abbr>("eTag_abbr", eTag_abbr),
      new PropertyZeroOrMore<CMisc>("cMisc2", cMisc2)
    }, firstToken, lastToken);
  }
  public Element_abbr2(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public ASTNode deepCopy() {
    return new Element_abbr2(cloneProperties(),firstToken,lastToken);
  }
  public STag_abbr getSTag_abbr() {
    return ((PropertyOne<STag_abbr>)getProperty("sTag_abbr")).getValue();
  }
  public ArrayList<CMisc> getCMisc1() {
    return ((PropertyZeroOrMore<CMisc>)getProperty("cMisc1")).getValue();
  }
  public ArrayList<Content_abbr_Choice1> getContent_abbr_Choice1() {
    return ((PropertyZeroOrMore<Content_abbr_Choice1>)getProperty("content_abbr_Choice1")).getValue();
  }
  public ETag_abbr getETag_abbr() {
    return ((PropertyOne<ETag_abbr>)getProperty("eTag_abbr")).getValue();
  }
  public ArrayList<CMisc> getCMisc2() {
    return ((PropertyZeroOrMore<CMisc>)getProperty("cMisc2")).getValue();
  }
}
