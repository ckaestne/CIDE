package tmp.generated_xhtml;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Content_object_Choice19 extends Content_object_Choice1 {
  public Content_object_Choice19(Element_h6 element_h6, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<Element_h6>("element_h6", element_h6)
    }, firstToken, lastToken);
  }
  public Content_object_Choice19(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public ASTNode deepCopy() {
    return new Content_object_Choice19(cloneProperties(),firstToken,lastToken);
  }
  public Element_h6 getElement_h6() {
    return ((PropertyOne<Element_h6>)getProperty("element_h6")).getValue();
  }
}
