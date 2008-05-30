package tmp.generated_people;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Element_b2 extends Element_b {
  public Element_b2(STag_b sTag_b, ArrayList<CMisc> cMisc1, ArrayList<Content_b_Seq1> content_b_Seq1, ETag_b eTag_b, ArrayList<CMisc> cMisc2, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<STag_b>("sTag_b", sTag_b),
      new PropertyZeroOrMore<CMisc>("cMisc1", cMisc1),
      new PropertyZeroOrMore<Content_b_Seq1>("content_b_Seq1", content_b_Seq1),
      new PropertyOne<ETag_b>("eTag_b", eTag_b),
      new PropertyZeroOrMore<CMisc>("cMisc2", cMisc2)
    }, firstToken, lastToken);
  }
  public Element_b2(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public ASTNode deepCopy() {
    return new Element_b2(cloneProperties(),firstToken,lastToken);
  }
  public STag_b getSTag_b() {
    return ((PropertyOne<STag_b>)getProperty("sTag_b")).getValue();
  }
  public ArrayList<CMisc> getCMisc1() {
    return ((PropertyZeroOrMore<CMisc>)getProperty("cMisc1")).getValue();
  }
  public ArrayList<Content_b_Seq1> getContent_b_Seq1() {
    return ((PropertyZeroOrMore<Content_b_Seq1>)getProperty("content_b_Seq1")).getValue();
  }
  public ETag_b getETag_b() {
    return ((PropertyOne<ETag_b>)getProperty("eTag_b")).getValue();
  }
  public ArrayList<CMisc> getCMisc2() {
    return ((PropertyZeroOrMore<CMisc>)getProperty("cMisc2")).getValue();
  }
}
