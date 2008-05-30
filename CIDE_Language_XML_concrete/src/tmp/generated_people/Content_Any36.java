package tmp.generated_people;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Content_Any36 extends Content_Any {
  public Content_Any36(Element_thead element_thead, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<Element_thead>("element_thead", element_thead)
    }, firstToken, lastToken);
  }
  public Content_Any36(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public ASTNode deepCopy() {
    return new Content_Any36(cloneProperties(),firstToken,lastToken);
  }
  public Element_thead getElement_thead() {
    return ((PropertyOne<Element_thead>)getProperty("element_thead")).getValue();
  }
}
