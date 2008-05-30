package tmp.generated_xhtml;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Content_Any7 extends Content_Any {
  public Content_Any7(Element_bdo element_bdo, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<Element_bdo>("element_bdo", element_bdo)
    }, firstToken, lastToken);
  }
  public Content_Any7(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public ASTNode deepCopy() {
    return new Content_Any7(cloneProperties(),firstToken,lastToken);
  }
  public Element_bdo getElement_bdo() {
    return ((PropertyOne<Element_bdo>)getProperty("element_bdo")).getValue();
  }
}
