package tmp.generated_people;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Element_h62 extends Element_h6 {
  public Element_h62(STag_h6 sTag_h6, ArrayList<CMisc> cMisc1, ArrayList<Content_h6_Seq1> content_h6_Seq1, ETag_h6 eTag_h6, ArrayList<CMisc> cMisc2, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<STag_h6>("sTag_h6", sTag_h6),
      new PropertyZeroOrMore<CMisc>("cMisc1", cMisc1),
      new PropertyZeroOrMore<Content_h6_Seq1>("content_h6_Seq1", content_h6_Seq1),
      new PropertyOne<ETag_h6>("eTag_h6", eTag_h6),
      new PropertyZeroOrMore<CMisc>("cMisc2", cMisc2)
    }, firstToken, lastToken);
  }
  public Element_h62(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public ASTNode deepCopy() {
    return new Element_h62(cloneProperties(),firstToken,lastToken);
  }
  public STag_h6 getSTag_h6() {
    return ((PropertyOne<STag_h6>)getProperty("sTag_h6")).getValue();
  }
  public ArrayList<CMisc> getCMisc1() {
    return ((PropertyZeroOrMore<CMisc>)getProperty("cMisc1")).getValue();
  }
  public ArrayList<Content_h6_Seq1> getContent_h6_Seq1() {
    return ((PropertyZeroOrMore<Content_h6_Seq1>)getProperty("content_h6_Seq1")).getValue();
  }
  public ETag_h6 getETag_h6() {
    return ((PropertyOne<ETag_h6>)getProperty("eTag_h6")).getValue();
  }
  public ArrayList<CMisc> getCMisc2() {
    return ((PropertyZeroOrMore<CMisc>)getProperty("cMisc2")).getValue();
  }
}
