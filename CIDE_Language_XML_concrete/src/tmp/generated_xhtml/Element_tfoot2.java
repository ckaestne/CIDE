package tmp.generated_xhtml;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Element_tfoot2 extends Element_tfoot {
  public Element_tfoot2(STag_tfoot sTag_tfoot, ArrayList<CMisc> cMisc1, ArrayList<Content_tfoot_Seq1> content_tfoot_Seq1, ETag_tfoot eTag_tfoot, ArrayList<CMisc> cMisc2, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<STag_tfoot>("sTag_tfoot", sTag_tfoot),
      new PropertyZeroOrMore<CMisc>("cMisc1", cMisc1),
      new PropertyOneOrMore<Content_tfoot_Seq1>("content_tfoot_Seq1", content_tfoot_Seq1),
      new PropertyOne<ETag_tfoot>("eTag_tfoot", eTag_tfoot),
      new PropertyZeroOrMore<CMisc>("cMisc2", cMisc2)
    }, firstToken, lastToken);
  }
  public Element_tfoot2(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public ASTNode deepCopy() {
    return new Element_tfoot2(cloneProperties(),firstToken,lastToken);
  }
  public STag_tfoot getSTag_tfoot() {
    return ((PropertyOne<STag_tfoot>)getProperty("sTag_tfoot")).getValue();
  }
  public ArrayList<CMisc> getCMisc1() {
    return ((PropertyZeroOrMore<CMisc>)getProperty("cMisc1")).getValue();
  }
  public ArrayList<Content_tfoot_Seq1> getContent_tfoot_Seq1() {
    return ((PropertyOneOrMore<Content_tfoot_Seq1>)getProperty("content_tfoot_Seq1")).getValue();
  }
  public ETag_tfoot getETag_tfoot() {
    return ((PropertyOne<ETag_tfoot>)getProperty("eTag_tfoot")).getValue();
  }
  public ArrayList<CMisc> getCMisc2() {
    return ((PropertyZeroOrMore<CMisc>)getProperty("cMisc2")).getValue();
  }
}
