package tmp.generated_people;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Element_fieldset2 extends Element_fieldset {
  public Element_fieldset2(STag_fieldset sTag_fieldset, ArrayList<CMisc> cMisc1, ArrayList<Content_fieldset_Seq1> content_fieldset_Seq1, ETag_fieldset eTag_fieldset, ArrayList<CMisc> cMisc2, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<STag_fieldset>("sTag_fieldset", sTag_fieldset),
      new PropertyZeroOrMore<CMisc>("cMisc1", cMisc1),
      new PropertyZeroOrMore<Content_fieldset_Seq1>("content_fieldset_Seq1", content_fieldset_Seq1),
      new PropertyOne<ETag_fieldset>("eTag_fieldset", eTag_fieldset),
      new PropertyZeroOrMore<CMisc>("cMisc2", cMisc2)
    }, firstToken, lastToken);
  }
  public Element_fieldset2(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public ASTNode deepCopy() {
    return new Element_fieldset2(cloneProperties(),firstToken,lastToken);
  }
  public STag_fieldset getSTag_fieldset() {
    return ((PropertyOne<STag_fieldset>)getProperty("sTag_fieldset")).getValue();
  }
  public ArrayList<CMisc> getCMisc1() {
    return ((PropertyZeroOrMore<CMisc>)getProperty("cMisc1")).getValue();
  }
  public ArrayList<Content_fieldset_Seq1> getContent_fieldset_Seq1() {
    return ((PropertyZeroOrMore<Content_fieldset_Seq1>)getProperty("content_fieldset_Seq1")).getValue();
  }
  public ETag_fieldset getETag_fieldset() {
    return ((PropertyOne<ETag_fieldset>)getProperty("eTag_fieldset")).getValue();
  }
  public ArrayList<CMisc> getCMisc2() {
    return ((PropertyZeroOrMore<CMisc>)getProperty("cMisc2")).getValue();
  }
}
