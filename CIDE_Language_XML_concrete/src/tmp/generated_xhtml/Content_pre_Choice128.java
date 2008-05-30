package tmp.generated_xhtml;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Content_pre_Choice128 extends Content_pre_Choice1 {
  public Content_pre_Choice128(Element_input element_input, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<Element_input>("element_input", element_input)
    }, firstToken, lastToken);
  }
  public Content_pre_Choice128(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public ASTNode deepCopy() {
    return new Content_pre_Choice128(cloneProperties(),firstToken,lastToken);
  }
  public Element_input getElement_input() {
    return ((PropertyOne<Element_input>)getProperty("element_input")).getValue();
  }
}
