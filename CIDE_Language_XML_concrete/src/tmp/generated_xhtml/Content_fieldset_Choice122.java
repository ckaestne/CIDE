package tmp.generated_xhtml;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Content_fieldset_Choice122 extends Content_fieldset_Choice1 {
  public Content_fieldset_Choice122(Element_br element_br, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<Element_br>("element_br", element_br)
    }, firstToken, lastToken);
  }
  public Content_fieldset_Choice122(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public ASTNode deepCopy() {
    return new Content_fieldset_Choice122(cloneProperties(),firstToken,lastToken);
  }
  public Element_br getElement_br() {
    return ((PropertyOne<Element_br>)getProperty("element_br")).getValue();
  }
}
