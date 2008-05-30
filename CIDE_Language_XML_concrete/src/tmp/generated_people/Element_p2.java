package tmp.generated_people;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Element_p2 extends Element_p {
  public Element_p2(STag_p sTag_p, ArrayList<CMisc> cMisc1, ArrayList<Content_p_Seq1> content_p_Seq1, ETag_p eTag_p, ArrayList<CMisc> cMisc2, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<STag_p>("sTag_p", sTag_p),
      new PropertyZeroOrMore<CMisc>("cMisc1", cMisc1),
      new PropertyZeroOrMore<Content_p_Seq1>("content_p_Seq1", content_p_Seq1),
      new PropertyOne<ETag_p>("eTag_p", eTag_p),
      new PropertyZeroOrMore<CMisc>("cMisc2", cMisc2)
    }, firstToken, lastToken);
  }
  public Element_p2(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public ASTNode deepCopy() {
    return new Element_p2(cloneProperties(),firstToken,lastToken);
  }
  public STag_p getSTag_p() {
    return ((PropertyOne<STag_p>)getProperty("sTag_p")).getValue();
  }
  public ArrayList<CMisc> getCMisc1() {
    return ((PropertyZeroOrMore<CMisc>)getProperty("cMisc1")).getValue();
  }
  public ArrayList<Content_p_Seq1> getContent_p_Seq1() {
    return ((PropertyZeroOrMore<Content_p_Seq1>)getProperty("content_p_Seq1")).getValue();
  }
  public ETag_p getETag_p() {
    return ((PropertyOne<ETag_p>)getProperty("eTag_p")).getValue();
  }
  public ArrayList<CMisc> getCMisc2() {
    return ((PropertyZeroOrMore<CMisc>)getProperty("cMisc2")).getValue();
  }
}
