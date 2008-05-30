package tmp.generated_xhtml;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Content_map_Choice24 extends Content_map_Choice2 {
  public Content_map_Choice24(Element_h3 element_h3, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<Element_h3>("element_h3", element_h3)
    }, firstToken, lastToken);
  }
  public Content_map_Choice24(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public ASTNode deepCopy() {
    return new Content_map_Choice24(cloneProperties(),firstToken,lastToken);
  }
  public Element_h3 getElement_h3() {
    return ((PropertyOne<Element_h3>)getProperty("element_h3")).getValue();
  }
}
