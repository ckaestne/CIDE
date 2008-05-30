package tmp.generated_people;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Element_birthdate2 extends Element_birthdate {
  public Element_birthdate2(STag_birthdate sTag_birthdate, ArrayList<CMisc> cMisc1, Content_birthdate_Seq1 content_birthdate_Seq1, ETag_birthdate eTag_birthdate, ArrayList<CMisc> cMisc2, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<STag_birthdate>("sTag_birthdate", sTag_birthdate),
      new PropertyZeroOrMore<CMisc>("cMisc1", cMisc1),
      new PropertyOne<Content_birthdate_Seq1>("content_birthdate_Seq1", content_birthdate_Seq1),
      new PropertyOne<ETag_birthdate>("eTag_birthdate", eTag_birthdate),
      new PropertyZeroOrMore<CMisc>("cMisc2", cMisc2)
    }, firstToken, lastToken);
  }
  public Element_birthdate2(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public ASTNode deepCopy() {
    return new Element_birthdate2(cloneProperties(),firstToken,lastToken);
  }
  public STag_birthdate getSTag_birthdate() {
    return ((PropertyOne<STag_birthdate>)getProperty("sTag_birthdate")).getValue();
  }
  public ArrayList<CMisc> getCMisc1() {
    return ((PropertyZeroOrMore<CMisc>)getProperty("cMisc1")).getValue();
  }
  public Content_birthdate_Seq1 getContent_birthdate_Seq1() {
    return ((PropertyOne<Content_birthdate_Seq1>)getProperty("content_birthdate_Seq1")).getValue();
  }
  public ETag_birthdate getETag_birthdate() {
    return ((PropertyOne<ETag_birthdate>)getProperty("eTag_birthdate")).getValue();
  }
  public ArrayList<CMisc> getCMisc2() {
    return ((PropertyZeroOrMore<CMisc>)getProperty("cMisc2")).getValue();
  }
}
