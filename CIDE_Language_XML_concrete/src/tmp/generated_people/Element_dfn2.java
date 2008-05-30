package tmp.generated_people;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Element_dfn2 extends Element_dfn {
  public Element_dfn2(STag_dfn sTag_dfn, ArrayList<CMisc> cMisc1, ArrayList<Content_dfn_Seq1> content_dfn_Seq1, ETag_dfn eTag_dfn, ArrayList<CMisc> cMisc2, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<STag_dfn>("sTag_dfn", sTag_dfn),
      new PropertyZeroOrMore<CMisc>("cMisc1", cMisc1),
      new PropertyZeroOrMore<Content_dfn_Seq1>("content_dfn_Seq1", content_dfn_Seq1),
      new PropertyOne<ETag_dfn>("eTag_dfn", eTag_dfn),
      new PropertyZeroOrMore<CMisc>("cMisc2", cMisc2)
    }, firstToken, lastToken);
  }
  public Element_dfn2(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public ASTNode deepCopy() {
    return new Element_dfn2(cloneProperties(),firstToken,lastToken);
  }
  public STag_dfn getSTag_dfn() {
    return ((PropertyOne<STag_dfn>)getProperty("sTag_dfn")).getValue();
  }
  public ArrayList<CMisc> getCMisc1() {
    return ((PropertyZeroOrMore<CMisc>)getProperty("cMisc1")).getValue();
  }
  public ArrayList<Content_dfn_Seq1> getContent_dfn_Seq1() {
    return ((PropertyZeroOrMore<Content_dfn_Seq1>)getProperty("content_dfn_Seq1")).getValue();
  }
  public ETag_dfn getETag_dfn() {
    return ((PropertyOne<ETag_dfn>)getProperty("eTag_dfn")).getValue();
  }
  public ArrayList<CMisc> getCMisc2() {
    return ((PropertyZeroOrMore<CMisc>)getProperty("cMisc2")).getValue();
  }
}
