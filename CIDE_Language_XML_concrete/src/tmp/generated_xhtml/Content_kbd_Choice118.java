package tmp.generated_xhtml;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Content_kbd_Choice118 extends Content_kbd_Choice1 {
  public Content_kbd_Choice118(Element_q element_q, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<Element_q>("element_q", element_q)
    }, firstToken, lastToken);
  }
  public Content_kbd_Choice118(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public ASTNode deepCopy() {
    return new Content_kbd_Choice118(cloneProperties(),firstToken,lastToken);
  }
  public Element_q getElement_q() {
    return ((PropertyOne<Element_q>)getProperty("element_q")).getValue();
  }
}
