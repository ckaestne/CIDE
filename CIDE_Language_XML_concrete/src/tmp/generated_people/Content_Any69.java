package tmp.generated_people;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Content_Any69 extends Content_Any {
  public Content_Any69(Element_select element_select, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<Element_select>("element_select", element_select)
    }, firstToken, lastToken);
  }
  public Content_Any69(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public ASTNode deepCopy() {
    return new Content_Any69(cloneProperties(),firstToken,lastToken);
  }
  public Element_select getElement_select() {
    return ((PropertyOne<Element_select>)getProperty("element_select")).getValue();
  }
}
