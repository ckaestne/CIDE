package tmp.generated_xhtml;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Content_bdo_Choice17 extends Content_bdo_Choice1 {
  public Content_bdo_Choice17(Element_object element_object, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<Element_object>("element_object", element_object)
    }, firstToken, lastToken);
  }
  public Content_bdo_Choice17(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public ASTNode deepCopy() {
    return new Content_bdo_Choice17(cloneProperties(),firstToken,lastToken);
  }
  public Element_object getElement_object() {
    return ((PropertyOne<Element_object>)getProperty("element_object")).getValue();
  }
}
