package tmp.generated_xhtml;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Content_div_Choice15 extends Content_div_Choice1 {
  public Content_div_Choice15(Element_h3 element_h3, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<Element_h3>("element_h3", element_h3)
    }, firstToken, lastToken);
  }
  public Content_div_Choice15(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public ASTNode deepCopy() {
    return new Content_div_Choice15(cloneProperties(),firstToken,lastToken);
  }
  public Element_h3 getElement_h3() {
    return ((PropertyOne<Element_h3>)getProperty("element_h3")).getValue();
  }
}
