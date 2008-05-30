package tmp.generated_xhtml;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Element_ins2 extends Element_ins {
  public Element_ins2(STag_ins sTag_ins, ArrayList<CMisc> cMisc1, ArrayList<Content_ins_Choice1> content_ins_Choice1, ETag_ins eTag_ins, ArrayList<CMisc> cMisc2, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<STag_ins>("sTag_ins", sTag_ins),
      new PropertyZeroOrMore<CMisc>("cMisc1", cMisc1),
      new PropertyZeroOrMore<Content_ins_Choice1>("content_ins_Choice1", content_ins_Choice1),
      new PropertyOne<ETag_ins>("eTag_ins", eTag_ins),
      new PropertyZeroOrMore<CMisc>("cMisc2", cMisc2)
    }, firstToken, lastToken);
  }
  public Element_ins2(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public ASTNode deepCopy() {
    return new Element_ins2(cloneProperties(),firstToken,lastToken);
  }
  public STag_ins getSTag_ins() {
    return ((PropertyOne<STag_ins>)getProperty("sTag_ins")).getValue();
  }
  public ArrayList<CMisc> getCMisc1() {
    return ((PropertyZeroOrMore<CMisc>)getProperty("cMisc1")).getValue();
  }
  public ArrayList<Content_ins_Choice1> getContent_ins_Choice1() {
    return ((PropertyZeroOrMore<Content_ins_Choice1>)getProperty("content_ins_Choice1")).getValue();
  }
  public ETag_ins getETag_ins() {
    return ((PropertyOne<ETag_ins>)getProperty("eTag_ins")).getValue();
  }
  public ArrayList<CMisc> getCMisc2() {
    return ((PropertyZeroOrMore<CMisc>)getProperty("cMisc2")).getValue();
  }
}
