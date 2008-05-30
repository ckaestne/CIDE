package tmp.generated_xhtml;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Content_li_Choice117 extends Content_li_Choice1 {
  public Content_li_Choice117(Element_fieldset element_fieldset, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<Element_fieldset>("element_fieldset", element_fieldset)
    }, firstToken, lastToken);
  }
  public Content_li_Choice117(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public ASTNode deepCopy() {
    return new Content_li_Choice117(cloneProperties(),firstToken,lastToken);
  }
  public Element_fieldset getElement_fieldset() {
    return ((PropertyOne<Element_fieldset>)getProperty("element_fieldset")).getValue();
  }
}
