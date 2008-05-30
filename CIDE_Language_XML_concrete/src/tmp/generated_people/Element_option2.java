package tmp.generated_people;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Element_option2 extends Element_option {
  public Element_option2(STag_option sTag_option, ArrayList<CMisc> cMisc1, Content_option_Seq1 content_option_Seq1, ETag_option eTag_option, ArrayList<CMisc> cMisc2, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<STag_option>("sTag_option", sTag_option),
      new PropertyZeroOrMore<CMisc>("cMisc1", cMisc1),
      new PropertyOne<Content_option_Seq1>("content_option_Seq1", content_option_Seq1),
      new PropertyOne<ETag_option>("eTag_option", eTag_option),
      new PropertyZeroOrMore<CMisc>("cMisc2", cMisc2)
    }, firstToken, lastToken);
  }
  public Element_option2(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public ASTNode deepCopy() {
    return new Element_option2(cloneProperties(),firstToken,lastToken);
  }
  public STag_option getSTag_option() {
    return ((PropertyOne<STag_option>)getProperty("sTag_option")).getValue();
  }
  public ArrayList<CMisc> getCMisc1() {
    return ((PropertyZeroOrMore<CMisc>)getProperty("cMisc1")).getValue();
  }
  public Content_option_Seq1 getContent_option_Seq1() {
    return ((PropertyOne<Content_option_Seq1>)getProperty("content_option_Seq1")).getValue();
  }
  public ETag_option getETag_option() {
    return ((PropertyOne<ETag_option>)getProperty("eTag_option")).getValue();
  }
  public ArrayList<CMisc> getCMisc2() {
    return ((PropertyZeroOrMore<CMisc>)getProperty("cMisc2")).getValue();
  }
}
