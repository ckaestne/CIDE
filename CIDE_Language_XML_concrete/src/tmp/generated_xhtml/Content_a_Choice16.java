package tmp.generated_xhtml;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Content_a_Choice16 extends Content_a_Choice1 {
  public Content_a_Choice16(Element_object element_object, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<Element_object>("element_object", element_object)
    }, firstToken, lastToken);
  }
  public Content_a_Choice16(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public ASTNode deepCopy() {
    return new Content_a_Choice16(cloneProperties(),firstToken,lastToken);
  }
  public Element_object getElement_object() {
    return ((PropertyOne<Element_object>)getProperty("element_object")).getValue();
  }
}
