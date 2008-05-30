package tmp.generated_xhtml;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Content_object_Choice12 extends Content_object_Choice1 {
  public Content_object_Choice12(Element_param element_param, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<Element_param>("element_param", element_param)
    }, firstToken, lastToken);
  }
  public Content_object_Choice12(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public ASTNode deepCopy() {
    return new Content_object_Choice12(cloneProperties(),firstToken,lastToken);
  }
  public Element_param getElement_param() {
    return ((PropertyOne<Element_param>)getProperty("element_param")).getValue();
  }
}
