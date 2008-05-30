package tmp.generated_xhtml;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Content_pre_Choice127 extends Content_pre_Choice1 {
  public Content_pre_Choice127(Element_script element_script, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<Element_script>("element_script", element_script)
    }, firstToken, lastToken);
  }
  public Content_pre_Choice127(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public ASTNode deepCopy() {
    return new Content_pre_Choice127(cloneProperties(),firstToken,lastToken);
  }
  public Element_script getElement_script() {
    return ((PropertyOne<Element_script>)getProperty("element_script")).getValue();
  }
}
