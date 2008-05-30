package tmp.generated_xhtml;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Content_li_Choice149 extends Content_li_Choice1 {
  public Content_li_Choice149(Element_button element_button, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<Element_button>("element_button", element_button)
    }, firstToken, lastToken);
  }
  public Content_li_Choice149(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public ASTNode deepCopy() {
    return new Content_li_Choice149(cloneProperties(),firstToken,lastToken);
  }
  public Element_button getElement_button() {
    return ((PropertyOne<Element_button>)getProperty("element_button")).getValue();
  }
}
