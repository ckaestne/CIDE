package tmp.generated_xhtml;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Content_sub_Choice113 extends Content_sub_Choice1 {
  public Content_sub_Choice113(Element_small element_small, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<Element_small>("element_small", element_small)
    }, firstToken, lastToken);
  }
  public Content_sub_Choice113(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public ASTNode deepCopy() {
    return new Content_sub_Choice113(cloneProperties(),firstToken,lastToken);
  }
  public Element_small getElement_small() {
    return ((PropertyOne<Element_small>)getProperty("element_small")).getValue();
  }
}
