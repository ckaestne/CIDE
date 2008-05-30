package tmp.generated_xhtml;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Content_dd_Choice119 extends Content_dd_Choice1 {
  public Content_dd_Choice119(Element_form element_form, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<Element_form>("element_form", element_form)
    }, firstToken, lastToken);
  }
  public Content_dd_Choice119(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public ASTNode deepCopy() {
    return new Content_dd_Choice119(cloneProperties(),firstToken,lastToken);
  }
  public Element_form getElement_form() {
    return ((PropertyOne<Element_form>)getProperty("element_form")).getValue();
  }
}
