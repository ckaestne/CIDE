package tmp.generated_xhtml;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Content_fieldset_Choice148 extends Content_fieldset_Choice1 {
  public Content_fieldset_Choice148(Element_textarea element_textarea, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<Element_textarea>("element_textarea", element_textarea)
    }, firstToken, lastToken);
  }
  public Content_fieldset_Choice148(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public ASTNode deepCopy() {
    return new Content_fieldset_Choice148(cloneProperties(),firstToken,lastToken);
  }
  public Element_textarea getElement_textarea() {
    return ((PropertyOne<Element_textarea>)getProperty("element_textarea")).getValue();
  }
}
