package tmp.generated_xhtml;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Content_Any45 extends Content_Any {
  public Content_Any45(Element_br element_br, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<Element_br>("element_br", element_br)
    }, firstToken, lastToken);
  }
  public Content_Any45(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public ASTNode deepCopy() {
    return new Content_Any45(cloneProperties(),firstToken,lastToken);
  }
  public Element_br getElement_br() {
    return ((PropertyOne<Element_br>)getProperty("element_br")).getValue();
  }
}
