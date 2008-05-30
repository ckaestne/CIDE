package tmp.generated_xhtml;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Element_caption2 extends Element_caption {
  public Element_caption2(STag_caption sTag_caption, ArrayList<CMisc> cMisc1, ArrayList<Content_caption_Choice1> content_caption_Choice1, ETag_caption eTag_caption, ArrayList<CMisc> cMisc2, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<STag_caption>("sTag_caption", sTag_caption),
      new PropertyZeroOrMore<CMisc>("cMisc1", cMisc1),
      new PropertyZeroOrMore<Content_caption_Choice1>("content_caption_Choice1", content_caption_Choice1),
      new PropertyOne<ETag_caption>("eTag_caption", eTag_caption),
      new PropertyZeroOrMore<CMisc>("cMisc2", cMisc2)
    }, firstToken, lastToken);
  }
  public Element_caption2(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public ASTNode deepCopy() {
    return new Element_caption2(cloneProperties(),firstToken,lastToken);
  }
  public STag_caption getSTag_caption() {
    return ((PropertyOne<STag_caption>)getProperty("sTag_caption")).getValue();
  }
  public ArrayList<CMisc> getCMisc1() {
    return ((PropertyZeroOrMore<CMisc>)getProperty("cMisc1")).getValue();
  }
  public ArrayList<Content_caption_Choice1> getContent_caption_Choice1() {
    return ((PropertyZeroOrMore<Content_caption_Choice1>)getProperty("content_caption_Choice1")).getValue();
  }
  public ETag_caption getETag_caption() {
    return ((PropertyOne<ETag_caption>)getProperty("eTag_caption")).getValue();
  }
  public ArrayList<CMisc> getCMisc2() {
    return ((PropertyZeroOrMore<CMisc>)getProperty("cMisc2")).getValue();
  }
}
