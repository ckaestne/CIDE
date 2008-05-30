package tmp.generated_people;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Element_h42 extends Element_h4 {
  public Element_h42(STag_h4 sTag_h4, ArrayList<CMisc> cMisc1, ArrayList<Content_h4_Seq1> content_h4_Seq1, ETag_h4 eTag_h4, ArrayList<CMisc> cMisc2, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<STag_h4>("sTag_h4", sTag_h4),
      new PropertyZeroOrMore<CMisc>("cMisc1", cMisc1),
      new PropertyZeroOrMore<Content_h4_Seq1>("content_h4_Seq1", content_h4_Seq1),
      new PropertyOne<ETag_h4>("eTag_h4", eTag_h4),
      new PropertyZeroOrMore<CMisc>("cMisc2", cMisc2)
    }, firstToken, lastToken);
  }
  public Element_h42(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public ASTNode deepCopy() {
    return new Element_h42(cloneProperties(),firstToken,lastToken);
  }
  public STag_h4 getSTag_h4() {
    return ((PropertyOne<STag_h4>)getProperty("sTag_h4")).getValue();
  }
  public ArrayList<CMisc> getCMisc1() {
    return ((PropertyZeroOrMore<CMisc>)getProperty("cMisc1")).getValue();
  }
  public ArrayList<Content_h4_Seq1> getContent_h4_Seq1() {
    return ((PropertyZeroOrMore<Content_h4_Seq1>)getProperty("content_h4_Seq1")).getValue();
  }
  public ETag_h4 getETag_h4() {
    return ((PropertyOne<ETag_h4>)getProperty("eTag_h4")).getValue();
  }
  public ArrayList<CMisc> getCMisc2() {
    return ((PropertyZeroOrMore<CMisc>)getProperty("cMisc2")).getValue();
  }
}
