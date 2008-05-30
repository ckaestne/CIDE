package tmp.generated_xhtml;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Content_fieldset_Choice131 extends Content_fieldset_Choice1 {
  public Content_fieldset_Choice131(Element_big element_big, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<Element_big>("element_big", element_big)
    }, firstToken, lastToken);
  }
  public Content_fieldset_Choice131(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public ASTNode deepCopy() {
    return new Content_fieldset_Choice131(cloneProperties(),firstToken,lastToken);
  }
  public Element_big getElement_big() {
    return ((PropertyOne<Element_big>)getProperty("element_big")).getValue();
  }
}
