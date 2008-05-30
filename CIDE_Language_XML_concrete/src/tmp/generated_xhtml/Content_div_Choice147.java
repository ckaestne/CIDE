package tmp.generated_xhtml;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Content_div_Choice147 extends Content_div_Choice1 {
  public Content_div_Choice147(Element_textarea element_textarea, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<Element_textarea>("element_textarea", element_textarea)
    }, firstToken, lastToken);
  }
  public Content_div_Choice147(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public ASTNode deepCopy() {
    return new Content_div_Choice147(cloneProperties(),firstToken,lastToken);
  }
  public Element_textarea getElement_textarea() {
    return ((PropertyOne<Element_textarea>)getProperty("element_textarea")).getValue();
  }
}
