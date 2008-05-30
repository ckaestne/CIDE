package tmp.generated_xhtml;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Content_fieldset_Choice149 extends Content_fieldset_Choice1 {
  public Content_fieldset_Choice149(Element_label element_label, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<Element_label>("element_label", element_label)
    }, firstToken, lastToken);
  }
  public Content_fieldset_Choice149(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public ASTNode deepCopy() {
    return new Content_fieldset_Choice149(cloneProperties(),firstToken,lastToken);
  }
  public Element_label getElement_label() {
    return ((PropertyOne<Element_label>)getProperty("element_label")).getValue();
  }
}
