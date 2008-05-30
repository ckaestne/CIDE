package tmp.generated_xhtml;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Content_a_Choice131 extends Content_a_Choice1 {
  public Content_a_Choice131(Element_ins element_ins, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<Element_ins>("element_ins", element_ins)
    }, firstToken, lastToken);
  }
  public Content_a_Choice131(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public ASTNode deepCopy() {
    return new Content_a_Choice131(cloneProperties(),firstToken,lastToken);
  }
  public Element_ins getElement_ins() {
    return ((PropertyOne<Element_ins>)getProperty("element_ins")).getValue();
  }
}
