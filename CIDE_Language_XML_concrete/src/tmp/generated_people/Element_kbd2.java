package tmp.generated_people;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Element_kbd2 extends Element_kbd {
  public Element_kbd2(STag_kbd sTag_kbd, ArrayList<CMisc> cMisc1, ArrayList<Content_kbd_Seq1> content_kbd_Seq1, ETag_kbd eTag_kbd, ArrayList<CMisc> cMisc2, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<STag_kbd>("sTag_kbd", sTag_kbd),
      new PropertyZeroOrMore<CMisc>("cMisc1", cMisc1),
      new PropertyZeroOrMore<Content_kbd_Seq1>("content_kbd_Seq1", content_kbd_Seq1),
      new PropertyOne<ETag_kbd>("eTag_kbd", eTag_kbd),
      new PropertyZeroOrMore<CMisc>("cMisc2", cMisc2)
    }, firstToken, lastToken);
  }
  public Element_kbd2(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public ASTNode deepCopy() {
    return new Element_kbd2(cloneProperties(),firstToken,lastToken);
  }
  public STag_kbd getSTag_kbd() {
    return ((PropertyOne<STag_kbd>)getProperty("sTag_kbd")).getValue();
  }
  public ArrayList<CMisc> getCMisc1() {
    return ((PropertyZeroOrMore<CMisc>)getProperty("cMisc1")).getValue();
  }
  public ArrayList<Content_kbd_Seq1> getContent_kbd_Seq1() {
    return ((PropertyZeroOrMore<Content_kbd_Seq1>)getProperty("content_kbd_Seq1")).getValue();
  }
  public ETag_kbd getETag_kbd() {
    return ((PropertyOne<ETag_kbd>)getProperty("eTag_kbd")).getValue();
  }
  public ArrayList<CMisc> getCMisc2() {
    return ((PropertyZeroOrMore<CMisc>)getProperty("cMisc2")).getValue();
  }
}
