package tmp.generated_xhtml;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Content_bdo_Choice128 extends Content_bdo_Choice1 {
  public Content_bdo_Choice128(Element_select element_select, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<Element_select>("element_select", element_select)
    }, firstToken, lastToken);
  }
  public Content_bdo_Choice128(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public ASTNode deepCopy() {
    return new Content_bdo_Choice128(cloneProperties(),firstToken,lastToken);
  }
  public Element_select getElement_select() {
    return ((PropertyOne<Element_select>)getProperty("element_select")).getValue();
  }
}
