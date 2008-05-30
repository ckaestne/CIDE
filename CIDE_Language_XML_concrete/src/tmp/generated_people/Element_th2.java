package tmp.generated_people;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Element_th2 extends Element_th {
  public Element_th2(STag_th sTag_th, ArrayList<CMisc> cMisc1, ArrayList<Content_th_Seq1> content_th_Seq1, ETag_th eTag_th, ArrayList<CMisc> cMisc2, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<STag_th>("sTag_th", sTag_th),
      new PropertyZeroOrMore<CMisc>("cMisc1", cMisc1),
      new PropertyZeroOrMore<Content_th_Seq1>("content_th_Seq1", content_th_Seq1),
      new PropertyOne<ETag_th>("eTag_th", eTag_th),
      new PropertyZeroOrMore<CMisc>("cMisc2", cMisc2)
    }, firstToken, lastToken);
  }
  public Element_th2(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public ASTNode deepCopy() {
    return new Element_th2(cloneProperties(),firstToken,lastToken);
  }
  public STag_th getSTag_th() {
    return ((PropertyOne<STag_th>)getProperty("sTag_th")).getValue();
  }
  public ArrayList<CMisc> getCMisc1() {
    return ((PropertyZeroOrMore<CMisc>)getProperty("cMisc1")).getValue();
  }
  public ArrayList<Content_th_Seq1> getContent_th_Seq1() {
    return ((PropertyZeroOrMore<Content_th_Seq1>)getProperty("content_th_Seq1")).getValue();
  }
  public ETag_th getETag_th() {
    return ((PropertyOne<ETag_th>)getProperty("eTag_th")).getValue();
  }
  public ArrayList<CMisc> getCMisc2() {
    return ((PropertyZeroOrMore<CMisc>)getProperty("cMisc2")).getValue();
  }
}
