package tmp.generated_people;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Content_Any32 extends Content_Any {
  public Content_Any32(Element_option element_option, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<Element_option>("element_option", element_option)
    }, firstToken, lastToken);
  }
  public Content_Any32(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public ASTNode deepCopy() {
    return new Content_Any32(cloneProperties(),firstToken,lastToken);
  }
  public Element_option getElement_option() {
    return ((PropertyOne<Element_option>)getProperty("element_option")).getValue();
  }
}
