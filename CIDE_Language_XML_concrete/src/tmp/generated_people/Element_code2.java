package tmp.generated_people;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Element_code2 extends Element_code {
  public Element_code2(STag_code sTag_code, ArrayList<CMisc> cMisc1, ArrayList<Content_code_Seq1> content_code_Seq1, ETag_code eTag_code, ArrayList<CMisc> cMisc2, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<STag_code>("sTag_code", sTag_code),
      new PropertyZeroOrMore<CMisc>("cMisc1", cMisc1),
      new PropertyZeroOrMore<Content_code_Seq1>("content_code_Seq1", content_code_Seq1),
      new PropertyOne<ETag_code>("eTag_code", eTag_code),
      new PropertyZeroOrMore<CMisc>("cMisc2", cMisc2)
    }, firstToken, lastToken);
  }
  public Element_code2(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public ASTNode deepCopy() {
    return new Element_code2(cloneProperties(),firstToken,lastToken);
  }
  public STag_code getSTag_code() {
    return ((PropertyOne<STag_code>)getProperty("sTag_code")).getValue();
  }
  public ArrayList<CMisc> getCMisc1() {
    return ((PropertyZeroOrMore<CMisc>)getProperty("cMisc1")).getValue();
  }
  public ArrayList<Content_code_Seq1> getContent_code_Seq1() {
    return ((PropertyZeroOrMore<Content_code_Seq1>)getProperty("content_code_Seq1")).getValue();
  }
  public ETag_code getETag_code() {
    return ((PropertyOne<ETag_code>)getProperty("eTag_code")).getValue();
  }
  public ArrayList<CMisc> getCMisc2() {
    return ((PropertyZeroOrMore<CMisc>)getProperty("cMisc2")).getValue();
  }
}
