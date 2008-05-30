package tmp.generated_xhtml;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Content_h1_Choice13 extends Content_h1_Choice1 {
  public Content_h1_Choice13(Element_br element_br, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<Element_br>("element_br", element_br)
    }, firstToken, lastToken);
  }
  public Content_h1_Choice13(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public ASTNode deepCopy() {
    return new Content_h1_Choice13(cloneProperties(),firstToken,lastToken);
  }
  public Element_br getElement_br() {
    return ((PropertyOne<Element_br>)getProperty("element_br")).getValue();
  }
}
