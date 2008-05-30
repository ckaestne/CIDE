package tmp.generated_people;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Content_Any31 extends Content_Any {
  public Content_Any31(Element_tbody element_tbody, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<Element_tbody>("element_tbody", element_tbody)
    }, firstToken, lastToken);
  }
  public Content_Any31(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public ASTNode deepCopy() {
    return new Content_Any31(cloneProperties(),firstToken,lastToken);
  }
  public Element_tbody getElement_tbody() {
    return ((PropertyOne<Element_tbody>)getProperty("element_tbody")).getValue();
  }
}
