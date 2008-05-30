package tmp.generated_people;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Element_socialsecuritynumber2 extends Element_socialsecuritynumber {
  public Element_socialsecuritynumber2(STag_socialsecuritynumber sTag_socialsecuritynumber, ArrayList<CMisc> cMisc1, Content_socialsecuritynumber_Seq1 content_socialsecuritynumber_Seq1, ETag_socialsecuritynumber eTag_socialsecuritynumber, ArrayList<CMisc> cMisc2, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<STag_socialsecuritynumber>("sTag_socialsecuritynumber", sTag_socialsecuritynumber),
      new PropertyZeroOrMore<CMisc>("cMisc1", cMisc1),
      new PropertyOne<Content_socialsecuritynumber_Seq1>("content_socialsecuritynumber_Seq1", content_socialsecuritynumber_Seq1),
      new PropertyOne<ETag_socialsecuritynumber>("eTag_socialsecuritynumber", eTag_socialsecuritynumber),
      new PropertyZeroOrMore<CMisc>("cMisc2", cMisc2)
    }, firstToken, lastToken);
  }
  public Element_socialsecuritynumber2(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public ASTNode deepCopy() {
    return new Element_socialsecuritynumber2(cloneProperties(),firstToken,lastToken);
  }
  public STag_socialsecuritynumber getSTag_socialsecuritynumber() {
    return ((PropertyOne<STag_socialsecuritynumber>)getProperty("sTag_socialsecuritynumber")).getValue();
  }
  public ArrayList<CMisc> getCMisc1() {
    return ((PropertyZeroOrMore<CMisc>)getProperty("cMisc1")).getValue();
  }
  public Content_socialsecuritynumber_Seq1 getContent_socialsecuritynumber_Seq1() {
    return ((PropertyOne<Content_socialsecuritynumber_Seq1>)getProperty("content_socialsecuritynumber_Seq1")).getValue();
  }
  public ETag_socialsecuritynumber getETag_socialsecuritynumber() {
    return ((PropertyOne<ETag_socialsecuritynumber>)getProperty("eTag_socialsecuritynumber")).getValue();
  }
  public ArrayList<CMisc> getCMisc2() {
    return ((PropertyZeroOrMore<CMisc>)getProperty("cMisc2")).getValue();
  }
}
