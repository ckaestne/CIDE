package tmp.generated_people;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Element_td2 extends Element_td {
  public Element_td2(STag_td sTag_td, ArrayList<CMisc> cMisc1, ArrayList<Content_td_Seq1> content_td_Seq1, ETag_td eTag_td, ArrayList<CMisc> cMisc2, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<STag_td>("sTag_td", sTag_td),
      new PropertyZeroOrMore<CMisc>("cMisc1", cMisc1),
      new PropertyZeroOrMore<Content_td_Seq1>("content_td_Seq1", content_td_Seq1),
      new PropertyOne<ETag_td>("eTag_td", eTag_td),
      new PropertyZeroOrMore<CMisc>("cMisc2", cMisc2)
    }, firstToken, lastToken);
  }
  public Element_td2(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public ASTNode deepCopy() {
    return new Element_td2(cloneProperties(),firstToken,lastToken);
  }
  public STag_td getSTag_td() {
    return ((PropertyOne<STag_td>)getProperty("sTag_td")).getValue();
  }
  public ArrayList<CMisc> getCMisc1() {
    return ((PropertyZeroOrMore<CMisc>)getProperty("cMisc1")).getValue();
  }
  public ArrayList<Content_td_Seq1> getContent_td_Seq1() {
    return ((PropertyZeroOrMore<Content_td_Seq1>)getProperty("content_td_Seq1")).getValue();
  }
  public ETag_td getETag_td() {
    return ((PropertyOne<ETag_td>)getProperty("eTag_td")).getValue();
  }
  public ArrayList<CMisc> getCMisc2() {
    return ((PropertyZeroOrMore<CMisc>)getProperty("cMisc2")).getValue();
  }
}
