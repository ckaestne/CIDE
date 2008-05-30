package tmp.generated_people;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Element_head2 extends Element_head {
  public Element_head2(STag_head sTag_head, ArrayList<CMisc> cMisc1, Content_head_Seq1 content_head_Seq1, ETag_head eTag_head, ArrayList<CMisc> cMisc2, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<STag_head>("sTag_head", sTag_head),
      new PropertyZeroOrMore<CMisc>("cMisc1", cMisc1),
      new PropertyOne<Content_head_Seq1>("content_head_Seq1", content_head_Seq1),
      new PropertyOne<ETag_head>("eTag_head", eTag_head),
      new PropertyZeroOrMore<CMisc>("cMisc2", cMisc2)
    }, firstToken, lastToken);
  }
  public Element_head2(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public ASTNode deepCopy() {
    return new Element_head2(cloneProperties(),firstToken,lastToken);
  }
  public STag_head getSTag_head() {
    return ((PropertyOne<STag_head>)getProperty("sTag_head")).getValue();
  }
  public ArrayList<CMisc> getCMisc1() {
    return ((PropertyZeroOrMore<CMisc>)getProperty("cMisc1")).getValue();
  }
  public Content_head_Seq1 getContent_head_Seq1() {
    return ((PropertyOne<Content_head_Seq1>)getProperty("content_head_Seq1")).getValue();
  }
  public ETag_head getETag_head() {
    return ((PropertyOne<ETag_head>)getProperty("eTag_head")).getValue();
  }
  public ArrayList<CMisc> getCMisc2() {
    return ((PropertyZeroOrMore<CMisc>)getProperty("cMisc2")).getValue();
  }
}
