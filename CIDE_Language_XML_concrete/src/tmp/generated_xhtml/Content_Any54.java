package tmp.generated_xhtml;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Content_Any54 extends Content_Any {
  public Content_Any54(Element_samp element_samp, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<Element_samp>("element_samp", element_samp)
    }, firstToken, lastToken);
  }
  public Content_Any54(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public ASTNode deepCopy() {
    return new Content_Any54(cloneProperties(),firstToken,lastToken);
  }
  public Element_samp getElement_samp() {
    return ((PropertyOne<Element_samp>)getProperty("element_samp")).getValue();
  }
}
