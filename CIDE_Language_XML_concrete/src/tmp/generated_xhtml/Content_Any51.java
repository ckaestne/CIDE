package tmp.generated_xhtml;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Content_Any51 extends Content_Any {
  public Content_Any51(Element_tr element_tr, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<Element_tr>("element_tr", element_tr)
    }, firstToken, lastToken);
  }
  public Content_Any51(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public ASTNode deepCopy() {
    return new Content_Any51(cloneProperties(),firstToken,lastToken);
  }
  public Element_tr getElement_tr() {
    return ((PropertyOne<Element_tr>)getProperty("element_tr")).getValue();
  }
}
