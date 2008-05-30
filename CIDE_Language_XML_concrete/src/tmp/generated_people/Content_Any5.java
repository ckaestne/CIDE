package tmp.generated_people;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Content_Any5 extends Content_Any {
  public Content_Any5(Element_small element_small, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<Element_small>("element_small", element_small)
    }, firstToken, lastToken);
  }
  public Content_Any5(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public ASTNode deepCopy() {
    return new Content_Any5(cloneProperties(),firstToken,lastToken);
  }
  public Element_small getElement_small() {
    return ((PropertyOne<Element_small>)getProperty("element_small")).getValue();
  }
}
