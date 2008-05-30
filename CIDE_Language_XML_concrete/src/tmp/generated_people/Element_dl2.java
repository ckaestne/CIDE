package tmp.generated_people;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Element_dl2 extends Element_dl {
  public Element_dl2(STag_dl sTag_dl, ArrayList<CMisc> cMisc1, ArrayList<Content_dl_Choice1> content_dl_Choice1, ETag_dl eTag_dl, ArrayList<CMisc> cMisc2, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<STag_dl>("sTag_dl", sTag_dl),
      new PropertyZeroOrMore<CMisc>("cMisc1", cMisc1),
      new PropertyOneOrMore<Content_dl_Choice1>("content_dl_Choice1", content_dl_Choice1),
      new PropertyOne<ETag_dl>("eTag_dl", eTag_dl),
      new PropertyZeroOrMore<CMisc>("cMisc2", cMisc2)
    }, firstToken, lastToken);
  }
  public Element_dl2(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public ASTNode deepCopy() {
    return new Element_dl2(cloneProperties(),firstToken,lastToken);
  }
  public STag_dl getSTag_dl() {
    return ((PropertyOne<STag_dl>)getProperty("sTag_dl")).getValue();
  }
  public ArrayList<CMisc> getCMisc1() {
    return ((PropertyZeroOrMore<CMisc>)getProperty("cMisc1")).getValue();
  }
  public ArrayList<Content_dl_Choice1> getContent_dl_Choice1() {
    return ((PropertyOneOrMore<Content_dl_Choice1>)getProperty("content_dl_Choice1")).getValue();
  }
  public ETag_dl getETag_dl() {
    return ((PropertyOne<ETag_dl>)getProperty("eTag_dl")).getValue();
  }
  public ArrayList<CMisc> getCMisc2() {
    return ((PropertyZeroOrMore<CMisc>)getProperty("cMisc2")).getValue();
  }
}
