package tmp.generated_xhtml;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Element_label2 extends Element_label {
  public Element_label2(STag_label sTag_label, ArrayList<CMisc> cMisc1, ArrayList<Content_label_Choice1> content_label_Choice1, ETag_label eTag_label, ArrayList<CMisc> cMisc2, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<STag_label>("sTag_label", sTag_label),
      new PropertyZeroOrMore<CMisc>("cMisc1", cMisc1),
      new PropertyZeroOrMore<Content_label_Choice1>("content_label_Choice1", content_label_Choice1),
      new PropertyOne<ETag_label>("eTag_label", eTag_label),
      new PropertyZeroOrMore<CMisc>("cMisc2", cMisc2)
    }, firstToken, lastToken);
  }
  public Element_label2(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public ASTNode deepCopy() {
    return new Element_label2(cloneProperties(),firstToken,lastToken);
  }
  public STag_label getSTag_label() {
    return ((PropertyOne<STag_label>)getProperty("sTag_label")).getValue();
  }
  public ArrayList<CMisc> getCMisc1() {
    return ((PropertyZeroOrMore<CMisc>)getProperty("cMisc1")).getValue();
  }
  public ArrayList<Content_label_Choice1> getContent_label_Choice1() {
    return ((PropertyZeroOrMore<Content_label_Choice1>)getProperty("content_label_Choice1")).getValue();
  }
  public ETag_label getETag_label() {
    return ((PropertyOne<ETag_label>)getProperty("eTag_label")).getValue();
  }
  public ArrayList<CMisc> getCMisc2() {
    return ((PropertyZeroOrMore<CMisc>)getProperty("cMisc2")).getValue();
  }
}
