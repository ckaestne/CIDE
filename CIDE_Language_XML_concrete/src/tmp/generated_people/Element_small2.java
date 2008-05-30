package tmp.generated_people;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Element_small2 extends Element_small {
  public Element_small2(STag_small sTag_small, ArrayList<CMisc> cMisc1, ArrayList<Content_small_Seq1> content_small_Seq1, ETag_small eTag_small, ArrayList<CMisc> cMisc2, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<STag_small>("sTag_small", sTag_small),
      new PropertyZeroOrMore<CMisc>("cMisc1", cMisc1),
      new PropertyZeroOrMore<Content_small_Seq1>("content_small_Seq1", content_small_Seq1),
      new PropertyOne<ETag_small>("eTag_small", eTag_small),
      new PropertyZeroOrMore<CMisc>("cMisc2", cMisc2)
    }, firstToken, lastToken);
  }
  public Element_small2(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public ASTNode deepCopy() {
    return new Element_small2(cloneProperties(),firstToken,lastToken);
  }
  public STag_small getSTag_small() {
    return ((PropertyOne<STag_small>)getProperty("sTag_small")).getValue();
  }
  public ArrayList<CMisc> getCMisc1() {
    return ((PropertyZeroOrMore<CMisc>)getProperty("cMisc1")).getValue();
  }
  public ArrayList<Content_small_Seq1> getContent_small_Seq1() {
    return ((PropertyZeroOrMore<Content_small_Seq1>)getProperty("content_small_Seq1")).getValue();
  }
  public ETag_small getETag_small() {
    return ((PropertyOne<ETag_small>)getProperty("eTag_small")).getValue();
  }
  public ArrayList<CMisc> getCMisc2() {
    return ((PropertyZeroOrMore<CMisc>)getProperty("cMisc2")).getValue();
  }
}
