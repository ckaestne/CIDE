package tmp.generated_xhtml;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Content_dd_Choice14 extends Content_dd_Choice1 {
  public Content_dd_Choice14(Element_h2 element_h2, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<Element_h2>("element_h2", element_h2)
    }, firstToken, lastToken);
  }
  public Content_dd_Choice14(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public ASTNode deepCopy() {
    return new Content_dd_Choice14(cloneProperties(),firstToken,lastToken);
  }
  public Element_h2 getElement_h2() {
    return ((PropertyOne<Element_h2>)getProperty("element_h2")).getValue();
  }
}
