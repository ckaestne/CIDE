package tmp.generated_xhtml;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Content_a_Choice119 extends Content_a_Choice1 {
  public Content_a_Choice119(Element_kbd element_kbd, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<Element_kbd>("element_kbd", element_kbd)
    }, firstToken, lastToken);
  }
  public Content_a_Choice119(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public ASTNode deepCopy() {
    return new Content_a_Choice119(cloneProperties(),firstToken,lastToken);
  }
  public Element_kbd getElement_kbd() {
    return ((PropertyOne<Element_kbd>)getProperty("element_kbd")).getValue();
  }
}
