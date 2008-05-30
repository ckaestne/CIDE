package tmp.generated_xhtml;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Content_button_Choice145 extends Content_button_Choice1 {
  public Content_button_Choice145(Element_script element_script, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<Element_script>("element_script", element_script)
    }, firstToken, lastToken);
  }
  public Content_button_Choice145(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public ASTNode deepCopy() {
    return new Content_button_Choice145(cloneProperties(),firstToken,lastToken);
  }
  public Element_script getElement_script() {
    return ((PropertyOne<Element_script>)getProperty("element_script")).getValue();
  }
}
