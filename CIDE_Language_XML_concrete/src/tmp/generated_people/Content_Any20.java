package tmp.generated_people;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Content_Any20 extends Content_Any {
  public Content_Any20(Element_body element_body, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<Element_body>("element_body", element_body)
    }, firstToken, lastToken);
  }
  public Content_Any20(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public ASTNode deepCopy() {
    return new Content_Any20(cloneProperties(),firstToken,lastToken);
  }
  public Element_body getElement_body() {
    return ((PropertyOne<Element_body>)getProperty("element_body")).getValue();
  }
}
