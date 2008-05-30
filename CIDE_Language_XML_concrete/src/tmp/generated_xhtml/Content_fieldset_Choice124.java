package tmp.generated_xhtml;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Content_fieldset_Choice124 extends Content_fieldset_Choice1 {
  public Content_fieldset_Choice124(Element_bdo element_bdo, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<Element_bdo>("element_bdo", element_bdo)
    }, firstToken, lastToken);
  }
  public Content_fieldset_Choice124(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public ASTNode deepCopy() {
    return new Content_fieldset_Choice124(cloneProperties(),firstToken,lastToken);
  }
  public Element_bdo getElement_bdo() {
    return ((PropertyOne<Element_bdo>)getProperty("element_bdo")).getValue();
  }
}
