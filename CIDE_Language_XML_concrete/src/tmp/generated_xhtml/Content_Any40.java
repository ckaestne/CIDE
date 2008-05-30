package tmp.generated_xhtml;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Content_Any40 extends Content_Any {
  public Content_Any40(Element_optgroup element_optgroup, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<Element_optgroup>("element_optgroup", element_optgroup)
    }, firstToken, lastToken);
  }
  public Content_Any40(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public ASTNode deepCopy() {
    return new Content_Any40(cloneProperties(),firstToken,lastToken);
  }
  public Element_optgroup getElement_optgroup() {
    return ((PropertyOne<Element_optgroup>)getProperty("element_optgroup")).getValue();
  }
}
