package tmp.generated_people;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Content_Any27 extends Content_Any {
  public Content_Any27(Element_base element_base, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<Element_base>("element_base", element_base)
    }, firstToken, lastToken);
  }
  public Content_Any27(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public ASTNode deepCopy() {
    return new Content_Any27(cloneProperties(),firstToken,lastToken);
  }
  public Element_base getElement_base() {
    return ((PropertyOne<Element_base>)getProperty("element_base")).getValue();
  }
}
