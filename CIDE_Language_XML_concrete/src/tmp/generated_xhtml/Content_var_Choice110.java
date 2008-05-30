package tmp.generated_xhtml;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Content_var_Choice110 extends Content_var_Choice1 {
  public Content_var_Choice110(Element_i element_i, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<Element_i>("element_i", element_i)
    }, firstToken, lastToken);
  }
  public Content_var_Choice110(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public ASTNode deepCopy() {
    return new Content_var_Choice110(cloneProperties(),firstToken,lastToken);
  }
  public Element_i getElement_i() {
    return ((PropertyOne<Element_i>)getProperty("element_i")).getValue();
  }
}
