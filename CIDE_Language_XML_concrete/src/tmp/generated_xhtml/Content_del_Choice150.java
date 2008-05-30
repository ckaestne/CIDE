package tmp.generated_xhtml;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Content_del_Choice150 extends Content_del_Choice1 {
  public Content_del_Choice150(Element_noscript element_noscript, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<Element_noscript>("element_noscript", element_noscript)
    }, firstToken, lastToken);
  }
  public Content_del_Choice150(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public ASTNode deepCopy() {
    return new Content_del_Choice150(cloneProperties(),firstToken,lastToken);
  }
  public Element_noscript getElement_noscript() {
    return ((PropertyOne<Element_noscript>)getProperty("element_noscript")).getValue();
  }
}
