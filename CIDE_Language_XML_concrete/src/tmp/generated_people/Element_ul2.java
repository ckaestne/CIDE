package tmp.generated_people;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Element_ul2 extends Element_ul {
  public Element_ul2(STag_ul sTag_ul, ArrayList<CMisc> cMisc1, ArrayList<Content_ul_Seq1> content_ul_Seq1, ETag_ul eTag_ul, ArrayList<CMisc> cMisc2, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<STag_ul>("sTag_ul", sTag_ul),
      new PropertyZeroOrMore<CMisc>("cMisc1", cMisc1),
      new PropertyOneOrMore<Content_ul_Seq1>("content_ul_Seq1", content_ul_Seq1),
      new PropertyOne<ETag_ul>("eTag_ul", eTag_ul),
      new PropertyZeroOrMore<CMisc>("cMisc2", cMisc2)
    }, firstToken, lastToken);
  }
  public Element_ul2(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public ASTNode deepCopy() {
    return new Element_ul2(cloneProperties(),firstToken,lastToken);
  }
  public STag_ul getSTag_ul() {
    return ((PropertyOne<STag_ul>)getProperty("sTag_ul")).getValue();
  }
  public ArrayList<CMisc> getCMisc1() {
    return ((PropertyZeroOrMore<CMisc>)getProperty("cMisc1")).getValue();
  }
  public ArrayList<Content_ul_Seq1> getContent_ul_Seq1() {
    return ((PropertyOneOrMore<Content_ul_Seq1>)getProperty("content_ul_Seq1")).getValue();
  }
  public ETag_ul getETag_ul() {
    return ((PropertyOne<ETag_ul>)getProperty("eTag_ul")).getValue();
  }
  public ArrayList<CMisc> getCMisc2() {
    return ((PropertyZeroOrMore<CMisc>)getProperty("cMisc2")).getValue();
  }
}
