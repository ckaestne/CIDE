package tmp.generated_xhtml;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Content_div_Choice143 extends Content_div_Choice1 {
  public Content_div_Choice143(Element_sub element_sub, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<Element_sub>("element_sub", element_sub)
    }, firstToken, lastToken);
  }
  public Content_div_Choice143(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public ASTNode deepCopy() {
    return new Content_div_Choice143(cloneProperties(),firstToken,lastToken);
  }
  public Element_sub getElement_sub() {
    return ((PropertyOne<Element_sub>)getProperty("element_sub")).getValue();
  }
}
