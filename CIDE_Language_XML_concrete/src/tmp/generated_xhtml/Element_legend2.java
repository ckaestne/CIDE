package tmp.generated_xhtml;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Element_legend2 extends Element_legend {
  public Element_legend2(STag_legend sTag_legend, ArrayList<CMisc> cMisc1, ArrayList<Content_legend_Choice1> content_legend_Choice1, ETag_legend eTag_legend, ArrayList<CMisc> cMisc2, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<STag_legend>("sTag_legend", sTag_legend),
      new PropertyZeroOrMore<CMisc>("cMisc1", cMisc1),
      new PropertyZeroOrMore<Content_legend_Choice1>("content_legend_Choice1", content_legend_Choice1),
      new PropertyOne<ETag_legend>("eTag_legend", eTag_legend),
      new PropertyZeroOrMore<CMisc>("cMisc2", cMisc2)
    }, firstToken, lastToken);
  }
  public Element_legend2(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public ASTNode deepCopy() {
    return new Element_legend2(cloneProperties(),firstToken,lastToken);
  }
  public STag_legend getSTag_legend() {
    return ((PropertyOne<STag_legend>)getProperty("sTag_legend")).getValue();
  }
  public ArrayList<CMisc> getCMisc1() {
    return ((PropertyZeroOrMore<CMisc>)getProperty("cMisc1")).getValue();
  }
  public ArrayList<Content_legend_Choice1> getContent_legend_Choice1() {
    return ((PropertyZeroOrMore<Content_legend_Choice1>)getProperty("content_legend_Choice1")).getValue();
  }
  public ETag_legend getETag_legend() {
    return ((PropertyOne<ETag_legend>)getProperty("eTag_legend")).getValue();
  }
  public ArrayList<CMisc> getCMisc2() {
    return ((PropertyZeroOrMore<CMisc>)getProperty("cMisc2")).getValue();
  }
}
