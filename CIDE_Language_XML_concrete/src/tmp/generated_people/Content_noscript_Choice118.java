package tmp.generated_people;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Content_noscript_Choice118 extends Content_noscript_Choice1 {
  public Content_noscript_Choice118(Element_form element_form, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<Element_form>("element_form", element_form)
    }, firstToken, lastToken);
  }
  public Content_noscript_Choice118(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public ASTNode deepCopy() {
    return new Content_noscript_Choice118(cloneProperties(),firstToken,lastToken);
  }
  public Element_form getElement_form() {
    return ((PropertyOne<Element_form>)getProperty("element_form")).getValue();
  }
}
