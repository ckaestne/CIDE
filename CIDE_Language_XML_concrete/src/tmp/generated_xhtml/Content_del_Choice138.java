package tmp.generated_xhtml;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Content_del_Choice138 extends Content_del_Choice1 {
  public Content_del_Choice138(Element_kbd element_kbd, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<Element_kbd>("element_kbd", element_kbd)
    }, firstToken, lastToken);
  }
  public Content_del_Choice138(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public ASTNode deepCopy() {
    return new Content_del_Choice138(cloneProperties(),firstToken,lastToken);
  }
  public Element_kbd getElement_kbd() {
    return ((PropertyOne<Element_kbd>)getProperty("element_kbd")).getValue();
  }
}
