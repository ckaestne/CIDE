package tmp.generated_xhtml;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Content_h4_Choice111 extends Content_h4_Choice1 {
  public Content_h4_Choice111(Element_b element_b, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<Element_b>("element_b", element_b)
    }, firstToken, lastToken);
  }
  public Content_h4_Choice111(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public ASTNode deepCopy() {
    return new Content_h4_Choice111(cloneProperties(),firstToken,lastToken);
  }
  public Element_b getElement_b() {
    return ((PropertyOne<Element_b>)getProperty("element_b")).getValue();
  }
}
