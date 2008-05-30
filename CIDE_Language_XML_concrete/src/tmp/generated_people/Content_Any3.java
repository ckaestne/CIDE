package tmp.generated_people;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Content_Any3 extends Content_Any {
  public Content_Any3(Element_textarea element_textarea, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<Element_textarea>("element_textarea", element_textarea)
    }, firstToken, lastToken);
  }
  public Content_Any3(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public ASTNode deepCopy() {
    return new Content_Any3(cloneProperties(),firstToken,lastToken);
  }
  public Element_textarea getElement_textarea() {
    return ((PropertyOne<Element_textarea>)getProperty("element_textarea")).getValue();
  }
}
