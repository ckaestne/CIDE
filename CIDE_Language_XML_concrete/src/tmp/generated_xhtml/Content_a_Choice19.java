package tmp.generated_xhtml;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Content_a_Choice19 extends Content_a_Choice1 {
  public Content_a_Choice19(Element_i element_i, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<Element_i>("element_i", element_i)
    }, firstToken, lastToken);
  }
  public Content_a_Choice19(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public ASTNode deepCopy() {
    return new Content_a_Choice19(cloneProperties(),firstToken,lastToken);
  }
  public Element_i getElement_i() {
    return ((PropertyOne<Element_i>)getProperty("element_i")).getValue();
  }
}
