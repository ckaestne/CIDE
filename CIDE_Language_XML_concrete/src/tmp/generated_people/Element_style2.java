package tmp.generated_people;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Element_style2 extends Element_style {
  public Element_style2(STag_style sTag_style, ArrayList<CMisc> cMisc1, Content_style_Seq1 content_style_Seq1, ETag_style eTag_style, ArrayList<CMisc> cMisc2, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<STag_style>("sTag_style", sTag_style),
      new PropertyZeroOrMore<CMisc>("cMisc1", cMisc1),
      new PropertyOne<Content_style_Seq1>("content_style_Seq1", content_style_Seq1),
      new PropertyOne<ETag_style>("eTag_style", eTag_style),
      new PropertyZeroOrMore<CMisc>("cMisc2", cMisc2)
    }, firstToken, lastToken);
  }
  public Element_style2(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public ASTNode deepCopy() {
    return new Element_style2(cloneProperties(),firstToken,lastToken);
  }
  public STag_style getSTag_style() {
    return ((PropertyOne<STag_style>)getProperty("sTag_style")).getValue();
  }
  public ArrayList<CMisc> getCMisc1() {
    return ((PropertyZeroOrMore<CMisc>)getProperty("cMisc1")).getValue();
  }
  public Content_style_Seq1 getContent_style_Seq1() {
    return ((PropertyOne<Content_style_Seq1>)getProperty("content_style_Seq1")).getValue();
  }
  public ETag_style getETag_style() {
    return ((PropertyOne<ETag_style>)getProperty("eTag_style")).getValue();
  }
  public ArrayList<CMisc> getCMisc2() {
    return ((PropertyZeroOrMore<CMisc>)getProperty("cMisc2")).getValue();
  }
}
