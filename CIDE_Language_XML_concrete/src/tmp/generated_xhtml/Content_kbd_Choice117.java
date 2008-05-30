package tmp.generated_xhtml;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Content_kbd_Choice117 extends Content_kbd_Choice1 {
  public Content_kbd_Choice117(Element_code element_code, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<Element_code>("element_code", element_code)
    }, firstToken, lastToken);
  }
  public Content_kbd_Choice117(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public ASTNode deepCopy() {
    return new Content_kbd_Choice117(cloneProperties(),firstToken,lastToken);
  }
  public Element_code getElement_code() {
    return ((PropertyOne<Element_code>)getProperty("element_code")).getValue();
  }
}
