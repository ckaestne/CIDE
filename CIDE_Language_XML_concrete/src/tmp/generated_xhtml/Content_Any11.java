package tmp.generated_xhtml;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Content_Any11 extends Content_Any {
  public Content_Any11(Element_dt element_dt, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<Element_dt>("element_dt", element_dt)
    }, firstToken, lastToken);
  }
  public Content_Any11(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public ASTNode deepCopy() {
    return new Content_Any11(cloneProperties(),firstToken,lastToken);
  }
  public Element_dt getElement_dt() {
    return ((PropertyOne<Element_dt>)getProperty("element_dt")).getValue();
  }
}
