package tmp.generated_xhtml;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Element_span2 extends Element_span {
  public Element_span2(STag_span sTag_span, ArrayList<CMisc> cMisc1, ArrayList<Content_span_Choice1> content_span_Choice1, ETag_span eTag_span, ArrayList<CMisc> cMisc2, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<STag_span>("sTag_span", sTag_span),
      new PropertyZeroOrMore<CMisc>("cMisc1", cMisc1),
      new PropertyZeroOrMore<Content_span_Choice1>("content_span_Choice1", content_span_Choice1),
      new PropertyOne<ETag_span>("eTag_span", eTag_span),
      new PropertyZeroOrMore<CMisc>("cMisc2", cMisc2)
    }, firstToken, lastToken);
  }
  public Element_span2(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public ASTNode deepCopy() {
    return new Element_span2(cloneProperties(),firstToken,lastToken);
  }
  public STag_span getSTag_span() {
    return ((PropertyOne<STag_span>)getProperty("sTag_span")).getValue();
  }
  public ArrayList<CMisc> getCMisc1() {
    return ((PropertyZeroOrMore<CMisc>)getProperty("cMisc1")).getValue();
  }
  public ArrayList<Content_span_Choice1> getContent_span_Choice1() {
    return ((PropertyZeroOrMore<Content_span_Choice1>)getProperty("content_span_Choice1")).getValue();
  }
  public ETag_span getETag_span() {
    return ((PropertyOne<ETag_span>)getProperty("eTag_span")).getValue();
  }
  public ArrayList<CMisc> getCMisc2() {
    return ((PropertyZeroOrMore<CMisc>)getProperty("cMisc2")).getValue();
  }
}
