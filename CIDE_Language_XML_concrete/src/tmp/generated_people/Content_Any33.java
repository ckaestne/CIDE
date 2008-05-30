package tmp.generated_people;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Content_Any33 extends Content_Any {
  public Content_Any33(Element_q element_q, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<Element_q>("element_q", element_q)
    }, firstToken, lastToken);
  }
  public Content_Any33(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public ASTNode deepCopy() {
    return new Content_Any33(cloneProperties(),firstToken,lastToken);
  }
  public Element_q getElement_q() {
    return ((PropertyOne<Element_q>)getProperty("element_q")).getValue();
  }
}
