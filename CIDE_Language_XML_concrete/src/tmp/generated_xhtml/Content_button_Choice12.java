package tmp.generated_xhtml;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Content_button_Choice12 extends Content_button_Choice1 {
  public Content_button_Choice12(Element_p element_p, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<Element_p>("element_p", element_p)
    }, firstToken, lastToken);
  }
  public Content_button_Choice12(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public ASTNode deepCopy() {
    return new Content_button_Choice12(cloneProperties(),firstToken,lastToken);
  }
  public Element_p getElement_p() {
    return ((PropertyOne<Element_p>)getProperty("element_p")).getValue();
  }
}
