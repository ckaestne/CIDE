package tmp.generated_people;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Content_body_Choice122 extends Content_body_Choice1 {
  public Content_body_Choice122(Element_script element_script, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<Element_script>("element_script", element_script)
    }, firstToken, lastToken);
  }
  public Content_body_Choice122(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public ASTNode deepCopy() {
    return new Content_body_Choice122(cloneProperties(),firstToken,lastToken);
  }
  public Element_script getElement_script() {
    return ((PropertyOne<Element_script>)getProperty("element_script")).getValue();
  }
}
