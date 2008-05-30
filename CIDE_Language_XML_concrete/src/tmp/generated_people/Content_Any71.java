package tmp.generated_people;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Content_Any71 extends Content_Any {
  public Content_Any71(Element_li element_li, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<Element_li>("element_li", element_li)
    }, firstToken, lastToken);
  }
  public Content_Any71(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public ASTNode deepCopy() {
    return new Content_Any71(cloneProperties(),firstToken,lastToken);
  }
  public Element_li getElement_li() {
    return ((PropertyOne<Element_li>)getProperty("element_li")).getValue();
  }
}
