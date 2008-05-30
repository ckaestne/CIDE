package tmp.generated_people;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Element_h12 extends Element_h1 {
  public Element_h12(STag_h1 sTag_h1, ArrayList<CMisc> cMisc1, ArrayList<Content_h1_Seq1> content_h1_Seq1, ETag_h1 eTag_h1, ArrayList<CMisc> cMisc2, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<STag_h1>("sTag_h1", sTag_h1),
      new PropertyZeroOrMore<CMisc>("cMisc1", cMisc1),
      new PropertyZeroOrMore<Content_h1_Seq1>("content_h1_Seq1", content_h1_Seq1),
      new PropertyOne<ETag_h1>("eTag_h1", eTag_h1),
      new PropertyZeroOrMore<CMisc>("cMisc2", cMisc2)
    }, firstToken, lastToken);
  }
  public Element_h12(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public ASTNode deepCopy() {
    return new Element_h12(cloneProperties(),firstToken,lastToken);
  }
  public STag_h1 getSTag_h1() {
    return ((PropertyOne<STag_h1>)getProperty("sTag_h1")).getValue();
  }
  public ArrayList<CMisc> getCMisc1() {
    return ((PropertyZeroOrMore<CMisc>)getProperty("cMisc1")).getValue();
  }
  public ArrayList<Content_h1_Seq1> getContent_h1_Seq1() {
    return ((PropertyZeroOrMore<Content_h1_Seq1>)getProperty("content_h1_Seq1")).getValue();
  }
  public ETag_h1 getETag_h1() {
    return ((PropertyOne<ETag_h1>)getProperty("eTag_h1")).getValue();
  }
  public ArrayList<CMisc> getCMisc2() {
    return ((PropertyZeroOrMore<CMisc>)getProperty("cMisc2")).getValue();
  }
}
