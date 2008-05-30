package tmp.generated_xhtml;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Content_Any65 extends Content_Any {
  public Content_Any65(Element_h2 element_h2, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<Element_h2>("element_h2", element_h2)
    }, firstToken, lastToken);
  }
  public Content_Any65(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public ASTNode deepCopy() {
    return new Content_Any65(cloneProperties(),firstToken,lastToken);
  }
  public Element_h2 getElement_h2() {
    return ((PropertyOne<Element_h2>)getProperty("element_h2")).getValue();
  }
}
