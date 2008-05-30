package tmp.generated_xhtml;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Element_textarea2 extends Element_textarea {
  public Element_textarea2(STag_textarea sTag_textarea, ArrayList<CMisc> cMisc1, Content_textarea_Seq1 content_textarea_Seq1, ETag_textarea eTag_textarea, ArrayList<CMisc> cMisc2, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<STag_textarea>("sTag_textarea", sTag_textarea),
      new PropertyZeroOrMore<CMisc>("cMisc1", cMisc1),
      new PropertyOne<Content_textarea_Seq1>("content_textarea_Seq1", content_textarea_Seq1),
      new PropertyOne<ETag_textarea>("eTag_textarea", eTag_textarea),
      new PropertyZeroOrMore<CMisc>("cMisc2", cMisc2)
    }, firstToken, lastToken);
  }
  public Element_textarea2(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public ASTNode deepCopy() {
    return new Element_textarea2(cloneProperties(),firstToken,lastToken);
  }
  public STag_textarea getSTag_textarea() {
    return ((PropertyOne<STag_textarea>)getProperty("sTag_textarea")).getValue();
  }
  public ArrayList<CMisc> getCMisc1() {
    return ((PropertyZeroOrMore<CMisc>)getProperty("cMisc1")).getValue();
  }
  public Content_textarea_Seq1 getContent_textarea_Seq1() {
    return ((PropertyOne<Content_textarea_Seq1>)getProperty("content_textarea_Seq1")).getValue();
  }
  public ETag_textarea getETag_textarea() {
    return ((PropertyOne<ETag_textarea>)getProperty("eTag_textarea")).getValue();
  }
  public ArrayList<CMisc> getCMisc2() {
    return ((PropertyZeroOrMore<CMisc>)getProperty("cMisc2")).getValue();
  }
}
