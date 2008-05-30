package tmp.generated_people;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Element_meta2 extends Element_meta {
  public Element_meta2(STag_meta sTag_meta, ArrayList<CMisc> cMisc1, ETag_meta eTag_meta, ArrayList<CMisc> cMisc2, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<STag_meta>("sTag_meta", sTag_meta),
      new PropertyZeroOrMore<CMisc>("cMisc1", cMisc1),
      new PropertyOne<ETag_meta>("eTag_meta", eTag_meta),
      new PropertyZeroOrMore<CMisc>("cMisc2", cMisc2)
    }, firstToken, lastToken);
  }
  public Element_meta2(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public ASTNode deepCopy() {
    return new Element_meta2(cloneProperties(),firstToken,lastToken);
  }
  public STag_meta getSTag_meta() {
    return ((PropertyOne<STag_meta>)getProperty("sTag_meta")).getValue();
  }
  public ArrayList<CMisc> getCMisc1() {
    return ((PropertyZeroOrMore<CMisc>)getProperty("cMisc1")).getValue();
  }
  public ETag_meta getETag_meta() {
    return ((PropertyOne<ETag_meta>)getProperty("eTag_meta")).getValue();
  }
  public ArrayList<CMisc> getCMisc2() {
    return ((PropertyZeroOrMore<CMisc>)getProperty("cMisc2")).getValue();
  }
}
