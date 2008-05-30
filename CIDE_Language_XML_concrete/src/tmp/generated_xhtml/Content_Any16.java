package tmp.generated_xhtml;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Content_Any16 extends Content_Any {
  public Content_Any16(Element_div element_div, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<Element_div>("element_div", element_div)
    }, firstToken, lastToken);
  }
  public Content_Any16(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public ASTNode deepCopy() {
    return new Content_Any16(cloneProperties(),firstToken,lastToken);
  }
  public Element_div getElement_div() {
    return ((PropertyOne<Element_div>)getProperty("element_div")).getValue();
  }
}
