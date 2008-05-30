package tmp.generated_xhtml;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Content_map_Choice212 extends Content_map_Choice2 {
  public Content_map_Choice212(Element_pre element_pre, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<Element_pre>("element_pre", element_pre)
    }, firstToken, lastToken);
  }
  public Content_map_Choice212(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public ASTNode deepCopy() {
    return new Content_map_Choice212(cloneProperties(),firstToken,lastToken);
  }
  public Element_pre getElement_pre() {
    return ((PropertyOne<Element_pre>)getProperty("element_pre")).getValue();
  }
}
