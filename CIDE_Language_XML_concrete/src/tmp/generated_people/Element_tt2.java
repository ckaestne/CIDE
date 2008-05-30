package tmp.generated_people;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Element_tt2 extends Element_tt {
  public Element_tt2(STag_tt sTag_tt, ArrayList<CMisc> cMisc1, ArrayList<Content_tt_Seq1> content_tt_Seq1, ETag_tt eTag_tt, ArrayList<CMisc> cMisc2, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<STag_tt>("sTag_tt", sTag_tt),
      new PropertyZeroOrMore<CMisc>("cMisc1", cMisc1),
      new PropertyZeroOrMore<Content_tt_Seq1>("content_tt_Seq1", content_tt_Seq1),
      new PropertyOne<ETag_tt>("eTag_tt", eTag_tt),
      new PropertyZeroOrMore<CMisc>("cMisc2", cMisc2)
    }, firstToken, lastToken);
  }
  public Element_tt2(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public ASTNode deepCopy() {
    return new Element_tt2(cloneProperties(),firstToken,lastToken);
  }
  public STag_tt getSTag_tt() {
    return ((PropertyOne<STag_tt>)getProperty("sTag_tt")).getValue();
  }
  public ArrayList<CMisc> getCMisc1() {
    return ((PropertyZeroOrMore<CMisc>)getProperty("cMisc1")).getValue();
  }
  public ArrayList<Content_tt_Seq1> getContent_tt_Seq1() {
    return ((PropertyZeroOrMore<Content_tt_Seq1>)getProperty("content_tt_Seq1")).getValue();
  }
  public ETag_tt getETag_tt() {
    return ((PropertyOne<ETag_tt>)getProperty("eTag_tt")).getValue();
  }
  public ArrayList<CMisc> getCMisc2() {
    return ((PropertyZeroOrMore<CMisc>)getProperty("cMisc2")).getValue();
  }
}
