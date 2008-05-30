package tmp.generated_people;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Element_acronym2 extends Element_acronym {
  public Element_acronym2(STag_acronym sTag_acronym, ArrayList<CMisc> cMisc1, ArrayList<Content_acronym_Seq1> content_acronym_Seq1, ETag_acronym eTag_acronym, ArrayList<CMisc> cMisc2, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<STag_acronym>("sTag_acronym", sTag_acronym),
      new PropertyZeroOrMore<CMisc>("cMisc1", cMisc1),
      new PropertyZeroOrMore<Content_acronym_Seq1>("content_acronym_Seq1", content_acronym_Seq1),
      new PropertyOne<ETag_acronym>("eTag_acronym", eTag_acronym),
      new PropertyZeroOrMore<CMisc>("cMisc2", cMisc2)
    }, firstToken, lastToken);
  }
  public Element_acronym2(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public ASTNode deepCopy() {
    return new Element_acronym2(cloneProperties(),firstToken,lastToken);
  }
  public STag_acronym getSTag_acronym() {
    return ((PropertyOne<STag_acronym>)getProperty("sTag_acronym")).getValue();
  }
  public ArrayList<CMisc> getCMisc1() {
    return ((PropertyZeroOrMore<CMisc>)getProperty("cMisc1")).getValue();
  }
  public ArrayList<Content_acronym_Seq1> getContent_acronym_Seq1() {
    return ((PropertyZeroOrMore<Content_acronym_Seq1>)getProperty("content_acronym_Seq1")).getValue();
  }
  public ETag_acronym getETag_acronym() {
    return ((PropertyOne<ETag_acronym>)getProperty("eTag_acronym")).getValue();
  }
  public ArrayList<CMisc> getCMisc2() {
    return ((PropertyZeroOrMore<CMisc>)getProperty("cMisc2")).getValue();
  }
}
