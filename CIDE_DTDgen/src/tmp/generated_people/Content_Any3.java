package tmp.generated_people;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Content_Any3 extends Content_Any {
  public Content_Any3(Element_name element_name, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<Element_name>("element_name", element_name)
    }, firstToken, lastToken);
  }
  public Content_Any3(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public ASTNode deepCopy() {
    return new Content_Any3(cloneProperties(),firstToken,lastToken);
  }
  public Element_name getElement_name() {
    return ((PropertyOne<Element_name>)getProperty("element_name")).getValue();
  }
}
