package tmp.generated_people;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Content_Any6 extends Content_Any {
  public Content_Any6(Element_area element_area, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<Element_area>("element_area", element_area)
    }, firstToken, lastToken);
  }
  public Content_Any6(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public ASTNode deepCopy() {
    return new Content_Any6(cloneProperties(),firstToken,lastToken);
  }
  public Element_area getElement_area() {
    return ((PropertyOne<Element_area>)getProperty("element_area")).getValue();
  }
}
