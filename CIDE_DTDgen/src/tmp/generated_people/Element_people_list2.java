package tmp.generated_people;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Element_people_list2 extends Element_people_list {
  public Element_people_list2(STag_people_list sTag_people_list, ArrayList<CMisc> cMisc1, Content_people_list_Seq1 content_people_list_Seq1, ETag_people_list eTag_people_list, ArrayList<CMisc> cMisc2, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<STag_people_list>("sTag_people_list", sTag_people_list),
      new PropertyZeroOrMore<CMisc>("cMisc1", cMisc1),
      new PropertyOne<Content_people_list_Seq1>("content_people_list_Seq1", content_people_list_Seq1),
      new PropertyOne<ETag_people_list>("eTag_people_list", eTag_people_list),
      new PropertyZeroOrMore<CMisc>("cMisc2", cMisc2)
    }, firstToken, lastToken);
  }
  public Element_people_list2(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public ASTNode deepCopy() {
    return new Element_people_list2(cloneProperties(),firstToken,lastToken);
  }
  public STag_people_list getSTag_people_list() {
    return ((PropertyOne<STag_people_list>)getProperty("sTag_people_list")).getValue();
  }
  public ArrayList<CMisc> getCMisc1() {
    return ((PropertyZeroOrMore<CMisc>)getProperty("cMisc1")).getValue();
  }
  public Content_people_list_Seq1 getContent_people_list_Seq1() {
    return ((PropertyOne<Content_people_list_Seq1>)getProperty("content_people_list_Seq1")).getValue();
  }
  public ETag_people_list getETag_people_list() {
    return ((PropertyOne<ETag_people_list>)getProperty("eTag_people_list")).getValue();
  }
  public ArrayList<CMisc> getCMisc2() {
    return ((PropertyZeroOrMore<CMisc>)getProperty("cMisc2")).getValue();
  }
}
