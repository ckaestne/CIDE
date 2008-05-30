package tmp.generated_people;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Element_colgroup2 extends Element_colgroup {
  public Element_colgroup2(STag_colgroup sTag_colgroup, ArrayList<CMisc> cMisc1, ArrayList<Content_colgroup_Seq1> content_colgroup_Seq1, ETag_colgroup eTag_colgroup, ArrayList<CMisc> cMisc2, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<STag_colgroup>("sTag_colgroup", sTag_colgroup),
      new PropertyZeroOrMore<CMisc>("cMisc1", cMisc1),
      new PropertyZeroOrMore<Content_colgroup_Seq1>("content_colgroup_Seq1", content_colgroup_Seq1),
      new PropertyOne<ETag_colgroup>("eTag_colgroup", eTag_colgroup),
      new PropertyZeroOrMore<CMisc>("cMisc2", cMisc2)
    }, firstToken, lastToken);
  }
  public Element_colgroup2(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public ASTNode deepCopy() {
    return new Element_colgroup2(cloneProperties(),firstToken,lastToken);
  }
  public STag_colgroup getSTag_colgroup() {
    return ((PropertyOne<STag_colgroup>)getProperty("sTag_colgroup")).getValue();
  }
  public ArrayList<CMisc> getCMisc1() {
    return ((PropertyZeroOrMore<CMisc>)getProperty("cMisc1")).getValue();
  }
  public ArrayList<Content_colgroup_Seq1> getContent_colgroup_Seq1() {
    return ((PropertyZeroOrMore<Content_colgroup_Seq1>)getProperty("content_colgroup_Seq1")).getValue();
  }
  public ETag_colgroup getETag_colgroup() {
    return ((PropertyOne<ETag_colgroup>)getProperty("eTag_colgroup")).getValue();
  }
  public ArrayList<CMisc> getCMisc2() {
    return ((PropertyZeroOrMore<CMisc>)getProperty("cMisc2")).getValue();
  }
}
