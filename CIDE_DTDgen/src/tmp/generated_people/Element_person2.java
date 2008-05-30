package tmp.generated_people;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Element_person2 extends Element_person {
  public Element_person2(STag_person sTag_person, ArrayList<CMisc> cMisc1, Content_person_Seq1 content_person_Seq1, ETag_person eTag_person, ArrayList<CMisc> cMisc2, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<STag_person>("sTag_person", sTag_person),
      new PropertyZeroOrMore<CMisc>("cMisc1", cMisc1),
      new PropertyOne<Content_person_Seq1>("content_person_Seq1", content_person_Seq1),
      new PropertyOne<ETag_person>("eTag_person", eTag_person),
      new PropertyZeroOrMore<CMisc>("cMisc2", cMisc2)
    }, firstToken, lastToken);
  }
  public Element_person2(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public ASTNode deepCopy() {
    return new Element_person2(cloneProperties(),firstToken,lastToken);
  }
  public STag_person getSTag_person() {
    return ((PropertyOne<STag_person>)getProperty("sTag_person")).getValue();
  }
  public ArrayList<CMisc> getCMisc1() {
    return ((PropertyZeroOrMore<CMisc>)getProperty("cMisc1")).getValue();
  }
  public Content_person_Seq1 getContent_person_Seq1() {
    return ((PropertyOne<Content_person_Seq1>)getProperty("content_person_Seq1")).getValue();
  }
  public ETag_person getETag_person() {
    return ((PropertyOne<ETag_person>)getProperty("eTag_person")).getValue();
  }
  public ArrayList<CMisc> getCMisc2() {
    return ((PropertyZeroOrMore<CMisc>)getProperty("cMisc2")).getValue();
  }
}
