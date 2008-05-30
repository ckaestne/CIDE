package tmp.generated_xhtml;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Content_fieldset_Choice17 extends Content_fieldset_Choice1 {
  public Content_fieldset_Choice17(Element_h4 element_h4, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<Element_h4>("element_h4", element_h4)
    }, firstToken, lastToken);
  }
  public Content_fieldset_Choice17(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public ASTNode deepCopy() {
    return new Content_fieldset_Choice17(cloneProperties(),firstToken,lastToken);
  }
  public Element_h4 getElement_h4() {
    return ((PropertyOne<Element_h4>)getProperty("element_h4")).getValue();
  }
}
