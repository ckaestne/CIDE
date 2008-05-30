package tmp.generated_xhtml;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Content_Any48 extends Content_Any {
  public Content_Any48(Element_param element_param, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<Element_param>("element_param", element_param)
    }, firstToken, lastToken);
  }
  public Content_Any48(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public ASTNode deepCopy() {
    return new Content_Any48(cloneProperties(),firstToken,lastToken);
  }
  public Element_param getElement_param() {
    return ((PropertyOne<Element_param>)getProperty("element_param")).getValue();
  }
}
