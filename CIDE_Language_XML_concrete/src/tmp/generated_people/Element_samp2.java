package tmp.generated_people;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Element_samp2 extends Element_samp {
  public Element_samp2(STag_samp sTag_samp, ArrayList<CMisc> cMisc1, ArrayList<Content_samp_Seq1> content_samp_Seq1, ETag_samp eTag_samp, ArrayList<CMisc> cMisc2, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<STag_samp>("sTag_samp", sTag_samp),
      new PropertyZeroOrMore<CMisc>("cMisc1", cMisc1),
      new PropertyZeroOrMore<Content_samp_Seq1>("content_samp_Seq1", content_samp_Seq1),
      new PropertyOne<ETag_samp>("eTag_samp", eTag_samp),
      new PropertyZeroOrMore<CMisc>("cMisc2", cMisc2)
    }, firstToken, lastToken);
  }
  public Element_samp2(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public ASTNode deepCopy() {
    return new Element_samp2(cloneProperties(),firstToken,lastToken);
  }
  public STag_samp getSTag_samp() {
    return ((PropertyOne<STag_samp>)getProperty("sTag_samp")).getValue();
  }
  public ArrayList<CMisc> getCMisc1() {
    return ((PropertyZeroOrMore<CMisc>)getProperty("cMisc1")).getValue();
  }
  public ArrayList<Content_samp_Seq1> getContent_samp_Seq1() {
    return ((PropertyZeroOrMore<Content_samp_Seq1>)getProperty("content_samp_Seq1")).getValue();
  }
  public ETag_samp getETag_samp() {
    return ((PropertyOne<ETag_samp>)getProperty("eTag_samp")).getValue();
  }
  public ArrayList<CMisc> getCMisc2() {
    return ((PropertyZeroOrMore<CMisc>)getProperty("cMisc2")).getValue();
  }
}
