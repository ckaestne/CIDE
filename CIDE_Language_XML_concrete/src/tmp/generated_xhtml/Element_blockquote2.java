package tmp.generated_xhtml;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Element_blockquote2 extends Element_blockquote {
  public Element_blockquote2(STag_blockquote sTag_blockquote, ArrayList<CMisc> cMisc1, ArrayList<Content_blockquote_Choice1> content_blockquote_Choice1, ETag_blockquote eTag_blockquote, ArrayList<CMisc> cMisc2, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<STag_blockquote>("sTag_blockquote", sTag_blockquote),
      new PropertyZeroOrMore<CMisc>("cMisc1", cMisc1),
      new PropertyZeroOrMore<Content_blockquote_Choice1>("content_blockquote_Choice1", content_blockquote_Choice1),
      new PropertyOne<ETag_blockquote>("eTag_blockquote", eTag_blockquote),
      new PropertyZeroOrMore<CMisc>("cMisc2", cMisc2)
    }, firstToken, lastToken);
  }
  public Element_blockquote2(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public ASTNode deepCopy() {
    return new Element_blockquote2(cloneProperties(),firstToken,lastToken);
  }
  public STag_blockquote getSTag_blockquote() {
    return ((PropertyOne<STag_blockquote>)getProperty("sTag_blockquote")).getValue();
  }
  public ArrayList<CMisc> getCMisc1() {
    return ((PropertyZeroOrMore<CMisc>)getProperty("cMisc1")).getValue();
  }
  public ArrayList<Content_blockquote_Choice1> getContent_blockquote_Choice1() {
    return ((PropertyZeroOrMore<Content_blockquote_Choice1>)getProperty("content_blockquote_Choice1")).getValue();
  }
  public ETag_blockquote getETag_blockquote() {
    return ((PropertyOne<ETag_blockquote>)getProperty("eTag_blockquote")).getValue();
  }
  public ArrayList<CMisc> getCMisc2() {
    return ((PropertyZeroOrMore<CMisc>)getProperty("cMisc2")).getValue();
  }
}
