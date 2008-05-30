package tmp.generated_xhtml;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Element_tbody2 extends Element_tbody {
  public Element_tbody2(STag_tbody sTag_tbody, ArrayList<CMisc> cMisc1, ArrayList<Content_tbody_Seq1> content_tbody_Seq1, ETag_tbody eTag_tbody, ArrayList<CMisc> cMisc2, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<STag_tbody>("sTag_tbody", sTag_tbody),
      new PropertyZeroOrMore<CMisc>("cMisc1", cMisc1),
      new PropertyOneOrMore<Content_tbody_Seq1>("content_tbody_Seq1", content_tbody_Seq1),
      new PropertyOne<ETag_tbody>("eTag_tbody", eTag_tbody),
      new PropertyZeroOrMore<CMisc>("cMisc2", cMisc2)
    }, firstToken, lastToken);
  }
  public Element_tbody2(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public ASTNode deepCopy() {
    return new Element_tbody2(cloneProperties(),firstToken,lastToken);
  }
  public STag_tbody getSTag_tbody() {
    return ((PropertyOne<STag_tbody>)getProperty("sTag_tbody")).getValue();
  }
  public ArrayList<CMisc> getCMisc1() {
    return ((PropertyZeroOrMore<CMisc>)getProperty("cMisc1")).getValue();
  }
  public ArrayList<Content_tbody_Seq1> getContent_tbody_Seq1() {
    return ((PropertyOneOrMore<Content_tbody_Seq1>)getProperty("content_tbody_Seq1")).getValue();
  }
  public ETag_tbody getETag_tbody() {
    return ((PropertyOne<ETag_tbody>)getProperty("eTag_tbody")).getValue();
  }
  public ArrayList<CMisc> getCMisc2() {
    return ((PropertyZeroOrMore<CMisc>)getProperty("cMisc2")).getValue();
  }
}
