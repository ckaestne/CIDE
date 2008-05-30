package tmp.generated_xhtml;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Content_map_Choice216 extends Content_map_Choice2 {
  public Content_map_Choice216(Element_fieldset element_fieldset, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<Element_fieldset>("element_fieldset", element_fieldset)
    }, firstToken, lastToken);
  }
  public Content_map_Choice216(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public ASTNode deepCopy() {
    return new Content_map_Choice216(cloneProperties(),firstToken,lastToken);
  }
  public Element_fieldset getElement_fieldset() {
    return ((PropertyOne<Element_fieldset>)getProperty("element_fieldset")).getValue();
  }
}
