package tmp.generated_xhtml;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Content_a_Choice117 extends Content_a_Choice1 {
  public Content_a_Choice117(Element_q element_q, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<Element_q>("element_q", element_q)
    }, firstToken, lastToken);
  }
  public Content_a_Choice117(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public ASTNode deepCopy() {
    return new Content_a_Choice117(cloneProperties(),firstToken,lastToken);
  }
  public Element_q getElement_q() {
    return ((PropertyOne<Element_q>)getProperty("element_q")).getValue();
  }
}
