package tmp.generated_people;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Content_Any37 extends Content_Any {
  public Content_Any37(Element_ul element_ul, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<Element_ul>("element_ul", element_ul)
    }, firstToken, lastToken);
  }
  public Content_Any37(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public ASTNode deepCopy() {
    return new Content_Any37(cloneProperties(),firstToken,lastToken);
  }
  public Element_ul getElement_ul() {
    return ((PropertyOne<Element_ul>)getProperty("element_ul")).getValue();
  }
}
