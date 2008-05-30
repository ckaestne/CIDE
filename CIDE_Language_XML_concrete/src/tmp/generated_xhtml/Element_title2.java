package tmp.generated_xhtml;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Element_title2 extends Element_title {
  public Element_title2(STag_title sTag_title, ArrayList<CMisc> cMisc1, Content_title_Seq1 content_title_Seq1, ETag_title eTag_title, ArrayList<CMisc> cMisc2, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<STag_title>("sTag_title", sTag_title),
      new PropertyZeroOrMore<CMisc>("cMisc1", cMisc1),
      new PropertyOne<Content_title_Seq1>("content_title_Seq1", content_title_Seq1),
      new PropertyOne<ETag_title>("eTag_title", eTag_title),
      new PropertyZeroOrMore<CMisc>("cMisc2", cMisc2)
    }, firstToken, lastToken);
  }
  public Element_title2(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public ASTNode deepCopy() {
    return new Element_title2(cloneProperties(),firstToken,lastToken);
  }
  public STag_title getSTag_title() {
    return ((PropertyOne<STag_title>)getProperty("sTag_title")).getValue();
  }
  public ArrayList<CMisc> getCMisc1() {
    return ((PropertyZeroOrMore<CMisc>)getProperty("cMisc1")).getValue();
  }
  public Content_title_Seq1 getContent_title_Seq1() {
    return ((PropertyOne<Content_title_Seq1>)getProperty("content_title_Seq1")).getValue();
  }
  public ETag_title getETag_title() {
    return ((PropertyOne<ETag_title>)getProperty("eTag_title")).getValue();
  }
  public ArrayList<CMisc> getCMisc2() {
    return ((PropertyZeroOrMore<CMisc>)getProperty("cMisc2")).getValue();
  }
}
