package tmp.generated_people;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Content_Any62 extends Content_Any {
  public Content_Any62(Element_h5 element_h5, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<Element_h5>("element_h5", element_h5)
    }, firstToken, lastToken);
  }
  public Content_Any62(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public ASTNode deepCopy() {
    return new Content_Any62(cloneProperties(),firstToken,lastToken);
  }
  public Element_h5 getElement_h5() {
    return ((PropertyOne<Element_h5>)getProperty("element_h5")).getValue();
  }
}
