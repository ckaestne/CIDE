package tmp.generated_xhtml;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Content_address_Choice127 extends Content_address_Choice1 {
  public Content_address_Choice127(Element_input element_input, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<Element_input>("element_input", element_input)
    }, firstToken, lastToken);
  }
  public Content_address_Choice127(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public ASTNode deepCopy() {
    return new Content_address_Choice127(cloneProperties(),firstToken,lastToken);
  }
  public Element_input getElement_input() {
    return ((PropertyOne<Element_input>)getProperty("element_input")).getValue();
  }
}
