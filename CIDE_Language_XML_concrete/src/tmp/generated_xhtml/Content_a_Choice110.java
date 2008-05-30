package tmp.generated_xhtml;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Content_a_Choice110 extends Content_a_Choice1 {
  public Content_a_Choice110(Element_b element_b, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<Element_b>("element_b", element_b)
    }, firstToken, lastToken);
  }
  public Content_a_Choice110(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public ASTNode deepCopy() {
    return new Content_a_Choice110(cloneProperties(),firstToken,lastToken);
  }
  public Element_b getElement_b() {
    return ((PropertyOne<Element_b>)getProperty("element_b")).getValue();
  }
}
