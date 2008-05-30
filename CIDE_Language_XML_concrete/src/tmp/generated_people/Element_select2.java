package tmp.generated_people;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Element_select2 extends Element_select {
  public Element_select2(STag_select sTag_select, ArrayList<CMisc> cMisc1, ArrayList<Content_select_Choice1> content_select_Choice1, ETag_select eTag_select, ArrayList<CMisc> cMisc2, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<STag_select>("sTag_select", sTag_select),
      new PropertyZeroOrMore<CMisc>("cMisc1", cMisc1),
      new PropertyOneOrMore<Content_select_Choice1>("content_select_Choice1", content_select_Choice1),
      new PropertyOne<ETag_select>("eTag_select", eTag_select),
      new PropertyZeroOrMore<CMisc>("cMisc2", cMisc2)
    }, firstToken, lastToken);
  }
  public Element_select2(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public ASTNode deepCopy() {
    return new Element_select2(cloneProperties(),firstToken,lastToken);
  }
  public STag_select getSTag_select() {
    return ((PropertyOne<STag_select>)getProperty("sTag_select")).getValue();
  }
  public ArrayList<CMisc> getCMisc1() {
    return ((PropertyZeroOrMore<CMisc>)getProperty("cMisc1")).getValue();
  }
  public ArrayList<Content_select_Choice1> getContent_select_Choice1() {
    return ((PropertyOneOrMore<Content_select_Choice1>)getProperty("content_select_Choice1")).getValue();
  }
  public ETag_select getETag_select() {
    return ((PropertyOne<ETag_select>)getProperty("eTag_select")).getValue();
  }
  public ArrayList<CMisc> getCMisc2() {
    return ((PropertyZeroOrMore<CMisc>)getProperty("cMisc2")).getValue();
  }
}
