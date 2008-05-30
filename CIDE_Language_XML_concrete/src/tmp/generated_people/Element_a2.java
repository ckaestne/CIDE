package tmp.generated_people;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Element_a2 extends Element_a {
  public Element_a2(STag_a sTag_a, ArrayList<CMisc> cMisc1, ArrayList<Content_a_Seq1> content_a_Seq1, ETag_a eTag_a, ArrayList<CMisc> cMisc2, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<STag_a>("sTag_a", sTag_a),
      new PropertyZeroOrMore<CMisc>("cMisc1", cMisc1),
      new PropertyZeroOrMore<Content_a_Seq1>("content_a_Seq1", content_a_Seq1),
      new PropertyOne<ETag_a>("eTag_a", eTag_a),
      new PropertyZeroOrMore<CMisc>("cMisc2", cMisc2)
    }, firstToken, lastToken);
  }
  public Element_a2(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public ASTNode deepCopy() {
    return new Element_a2(cloneProperties(),firstToken,lastToken);
  }
  public STag_a getSTag_a() {
    return ((PropertyOne<STag_a>)getProperty("sTag_a")).getValue();
  }
  public ArrayList<CMisc> getCMisc1() {
    return ((PropertyZeroOrMore<CMisc>)getProperty("cMisc1")).getValue();
  }
  public ArrayList<Content_a_Seq1> getContent_a_Seq1() {
    return ((PropertyZeroOrMore<Content_a_Seq1>)getProperty("content_a_Seq1")).getValue();
  }
  public ETag_a getETag_a() {
    return ((PropertyOne<ETag_a>)getProperty("eTag_a")).getValue();
  }
  public ArrayList<CMisc> getCMisc2() {
    return ((PropertyZeroOrMore<CMisc>)getProperty("cMisc2")).getValue();
  }
}
