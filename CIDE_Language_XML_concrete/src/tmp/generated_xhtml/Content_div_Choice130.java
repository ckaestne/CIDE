package tmp.generated_xhtml;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Content_div_Choice130 extends Content_div_Choice1 {
  public Content_div_Choice130(Element_big element_big, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<Element_big>("element_big", element_big)
    }, firstToken, lastToken);
  }
  public Content_div_Choice130(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public ASTNode deepCopy() {
    return new Content_div_Choice130(cloneProperties(),firstToken,lastToken);
  }
  public Element_big getElement_big() {
    return ((PropertyOne<Element_big>)getProperty("element_big")).getValue();
  }
}
