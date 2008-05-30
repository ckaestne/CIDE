package tmp.generated_xhtml;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Content_button_Choice120 extends Content_button_Choice1 {
  public Content_button_Choice120(Element_bdo element_bdo, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<Element_bdo>("element_bdo", element_bdo)
    }, firstToken, lastToken);
  }
  public Content_button_Choice120(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public ASTNode deepCopy() {
    return new Content_button_Choice120(cloneProperties(),firstToken,lastToken);
  }
  public Element_bdo getElement_bdo() {
    return ((PropertyOne<Element_bdo>)getProperty("element_bdo")).getValue();
  }
}
