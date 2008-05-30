package tmp.generated_xhtml;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Content_Any25 extends Content_Any {
  public Content_Any25(Element_head element_head, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<Element_head>("element_head", element_head)
    }, firstToken, lastToken);
  }
  public Content_Any25(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public ASTNode deepCopy() {
    return new Content_Any25(cloneProperties(),firstToken,lastToken);
  }
  public Element_head getElement_head() {
    return ((PropertyOne<Element_head>)getProperty("element_head")).getValue();
  }
}
