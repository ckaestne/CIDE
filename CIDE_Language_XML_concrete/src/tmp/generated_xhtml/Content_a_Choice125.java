package tmp.generated_xhtml;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Content_a_Choice125 extends Content_a_Choice1 {
  public Content_a_Choice125(Element_sup element_sup, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<Element_sup>("element_sup", element_sup)
    }, firstToken, lastToken);
  }
  public Content_a_Choice125(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public ASTNode deepCopy() {
    return new Content_a_Choice125(cloneProperties(),firstToken,lastToken);
  }
  public Element_sup getElement_sup() {
    return ((PropertyOne<Element_sup>)getProperty("element_sup")).getValue();
  }
}
