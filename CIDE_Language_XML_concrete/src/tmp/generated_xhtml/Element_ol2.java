package tmp.generated_xhtml;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Element_ol2 extends Element_ol {
  public Element_ol2(STag_ol sTag_ol, ArrayList<CMisc> cMisc1, ArrayList<Content_ol_Seq1> content_ol_Seq1, ETag_ol eTag_ol, ArrayList<CMisc> cMisc2, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<STag_ol>("sTag_ol", sTag_ol),
      new PropertyZeroOrMore<CMisc>("cMisc1", cMisc1),
      new PropertyOneOrMore<Content_ol_Seq1>("content_ol_Seq1", content_ol_Seq1),
      new PropertyOne<ETag_ol>("eTag_ol", eTag_ol),
      new PropertyZeroOrMore<CMisc>("cMisc2", cMisc2)
    }, firstToken, lastToken);
  }
  public Element_ol2(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public ASTNode deepCopy() {
    return new Element_ol2(cloneProperties(),firstToken,lastToken);
  }
  public STag_ol getSTag_ol() {
    return ((PropertyOne<STag_ol>)getProperty("sTag_ol")).getValue();
  }
  public ArrayList<CMisc> getCMisc1() {
    return ((PropertyZeroOrMore<CMisc>)getProperty("cMisc1")).getValue();
  }
  public ArrayList<Content_ol_Seq1> getContent_ol_Seq1() {
    return ((PropertyOneOrMore<Content_ol_Seq1>)getProperty("content_ol_Seq1")).getValue();
  }
  public ETag_ol getETag_ol() {
    return ((PropertyOne<ETag_ol>)getProperty("eTag_ol")).getValue();
  }
  public ArrayList<CMisc> getCMisc2() {
    return ((PropertyZeroOrMore<CMisc>)getProperty("cMisc2")).getValue();
  }
}
