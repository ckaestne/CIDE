package tmp.generated_people;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Content_Any76 extends Content_Any {
  public Content_Any76(Element_input element_input, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<Element_input>("element_input", element_input)
    }, firstToken, lastToken);
  }
  public Content_Any76(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public ASTNode deepCopy() {
    return new Content_Any76(cloneProperties(),firstToken,lastToken);
  }
  public Element_input getElement_input() {
    return ((PropertyOne<Element_input>)getProperty("element_input")).getValue();
  }
}
