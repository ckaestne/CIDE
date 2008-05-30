package tmp.generated_people;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Element_optgroup2 extends Element_optgroup {
  public Element_optgroup2(STag_optgroup sTag_optgroup, ArrayList<CMisc> cMisc1, ArrayList<Content_optgroup_Seq1> content_optgroup_Seq1, ETag_optgroup eTag_optgroup, ArrayList<CMisc> cMisc2, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<STag_optgroup>("sTag_optgroup", sTag_optgroup),
      new PropertyZeroOrMore<CMisc>("cMisc1", cMisc1),
      new PropertyOneOrMore<Content_optgroup_Seq1>("content_optgroup_Seq1", content_optgroup_Seq1),
      new PropertyOne<ETag_optgroup>("eTag_optgroup", eTag_optgroup),
      new PropertyZeroOrMore<CMisc>("cMisc2", cMisc2)
    }, firstToken, lastToken);
  }
  public Element_optgroup2(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public ASTNode deepCopy() {
    return new Element_optgroup2(cloneProperties(),firstToken,lastToken);
  }
  public STag_optgroup getSTag_optgroup() {
    return ((PropertyOne<STag_optgroup>)getProperty("sTag_optgroup")).getValue();
  }
  public ArrayList<CMisc> getCMisc1() {
    return ((PropertyZeroOrMore<CMisc>)getProperty("cMisc1")).getValue();
  }
  public ArrayList<Content_optgroup_Seq1> getContent_optgroup_Seq1() {
    return ((PropertyOneOrMore<Content_optgroup_Seq1>)getProperty("content_optgroup_Seq1")).getValue();
  }
  public ETag_optgroup getETag_optgroup() {
    return ((PropertyOne<ETag_optgroup>)getProperty("eTag_optgroup")).getValue();
  }
  public ArrayList<CMisc> getCMisc2() {
    return ((PropertyZeroOrMore<CMisc>)getProperty("cMisc2")).getValue();
  }
}
