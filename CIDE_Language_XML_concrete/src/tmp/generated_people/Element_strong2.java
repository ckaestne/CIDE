package tmp.generated_people;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Element_strong2 extends Element_strong {
  public Element_strong2(STag_strong sTag_strong, ArrayList<CMisc> cMisc1, ArrayList<Content_strong_Seq1> content_strong_Seq1, ETag_strong eTag_strong, ArrayList<CMisc> cMisc2, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<STag_strong>("sTag_strong", sTag_strong),
      new PropertyZeroOrMore<CMisc>("cMisc1", cMisc1),
      new PropertyZeroOrMore<Content_strong_Seq1>("content_strong_Seq1", content_strong_Seq1),
      new PropertyOne<ETag_strong>("eTag_strong", eTag_strong),
      new PropertyZeroOrMore<CMisc>("cMisc2", cMisc2)
    }, firstToken, lastToken);
  }
  public Element_strong2(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public ASTNode deepCopy() {
    return new Element_strong2(cloneProperties(),firstToken,lastToken);
  }
  public STag_strong getSTag_strong() {
    return ((PropertyOne<STag_strong>)getProperty("sTag_strong")).getValue();
  }
  public ArrayList<CMisc> getCMisc1() {
    return ((PropertyZeroOrMore<CMisc>)getProperty("cMisc1")).getValue();
  }
  public ArrayList<Content_strong_Seq1> getContent_strong_Seq1() {
    return ((PropertyZeroOrMore<Content_strong_Seq1>)getProperty("content_strong_Seq1")).getValue();
  }
  public ETag_strong getETag_strong() {
    return ((PropertyOne<ETag_strong>)getProperty("eTag_strong")).getValue();
  }
  public ArrayList<CMisc> getCMisc2() {
    return ((PropertyZeroOrMore<CMisc>)getProperty("cMisc2")).getValue();
  }
}
