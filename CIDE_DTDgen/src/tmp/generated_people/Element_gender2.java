package tmp.generated_people;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Element_gender2 extends Element_gender {
  public Element_gender2(STag_gender sTag_gender, ArrayList<CMisc> cMisc1, Content_gender_Seq1 content_gender_Seq1, ETag_gender eTag_gender, ArrayList<CMisc> cMisc2, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<STag_gender>("sTag_gender", sTag_gender),
      new PropertyZeroOrMore<CMisc>("cMisc1", cMisc1),
      new PropertyOne<Content_gender_Seq1>("content_gender_Seq1", content_gender_Seq1),
      new PropertyOne<ETag_gender>("eTag_gender", eTag_gender),
      new PropertyZeroOrMore<CMisc>("cMisc2", cMisc2)
    }, firstToken, lastToken);
  }
  public Element_gender2(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public ASTNode deepCopy() {
    return new Element_gender2(cloneProperties(),firstToken,lastToken);
  }
  public STag_gender getSTag_gender() {
    return ((PropertyOne<STag_gender>)getProperty("sTag_gender")).getValue();
  }
  public ArrayList<CMisc> getCMisc1() {
    return ((PropertyZeroOrMore<CMisc>)getProperty("cMisc1")).getValue();
  }
  public Content_gender_Seq1 getContent_gender_Seq1() {
    return ((PropertyOne<Content_gender_Seq1>)getProperty("content_gender_Seq1")).getValue();
  }
  public ETag_gender getETag_gender() {
    return ((PropertyOne<ETag_gender>)getProperty("eTag_gender")).getValue();
  }
  public ArrayList<CMisc> getCMisc2() {
    return ((PropertyZeroOrMore<CMisc>)getProperty("cMisc2")).getValue();
  }
}
