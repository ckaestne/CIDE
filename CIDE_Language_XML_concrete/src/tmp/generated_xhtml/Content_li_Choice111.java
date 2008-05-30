package tmp.generated_xhtml;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Content_li_Choice111 extends Content_li_Choice1 {
  public Content_li_Choice111(Element_ol element_ol, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<Element_ol>("element_ol", element_ol)
    }, firstToken, lastToken);
  }
  public Content_li_Choice111(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public ASTNode deepCopy() {
    return new Content_li_Choice111(cloneProperties(),firstToken,lastToken);
  }
  public Element_ol getElement_ol() {
    return ((PropertyOne<Element_ol>)getProperty("element_ol")).getValue();
  }
}
