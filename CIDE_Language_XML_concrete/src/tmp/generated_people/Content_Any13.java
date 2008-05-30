package tmp.generated_people;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Content_Any13 extends Content_Any {
  public Content_Any13(Element_title element_title, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<Element_title>("element_title", element_title)
    }, firstToken, lastToken);
  }
  public Content_Any13(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public ASTNode deepCopy() {
    return new Content_Any13(cloneProperties(),firstToken,lastToken);
  }
  public Element_title getElement_title() {
    return ((PropertyOne<Element_title>)getProperty("element_title")).getValue();
  }
}
