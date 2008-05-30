package tmp.generated_xhtml;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Content_del_Choice110 extends Content_del_Choice1 {
  public Content_del_Choice110(Element_ul element_ul, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<Element_ul>("element_ul", element_ul)
    }, firstToken, lastToken);
  }
  public Content_del_Choice110(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public ASTNode deepCopy() {
    return new Content_del_Choice110(cloneProperties(),firstToken,lastToken);
  }
  public Element_ul getElement_ul() {
    return ((PropertyOne<Element_ul>)getProperty("element_ul")).getValue();
  }
}
