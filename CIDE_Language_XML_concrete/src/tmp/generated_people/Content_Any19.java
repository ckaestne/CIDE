package tmp.generated_people;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Content_Any19 extends Content_Any {
  public Content_Any19(Element_kbd element_kbd, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<Element_kbd>("element_kbd", element_kbd)
    }, firstToken, lastToken);
  }
  public Content_Any19(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public ASTNode deepCopy() {
    return new Content_Any19(cloneProperties(),firstToken,lastToken);
  }
  public Element_kbd getElement_kbd() {
    return ((PropertyOne<Element_kbd>)getProperty("element_kbd")).getValue();
  }
}
