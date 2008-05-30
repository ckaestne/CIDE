package tmp.generated_xhtml;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Content_del_Choice137 extends Content_del_Choice1 {
  public Content_del_Choice137(Element_samp element_samp, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<Element_samp>("element_samp", element_samp)
    }, firstToken, lastToken);
  }
  public Content_del_Choice137(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public ASTNode deepCopy() {
    return new Content_del_Choice137(cloneProperties(),firstToken,lastToken);
  }
  public Element_samp getElement_samp() {
    return ((PropertyOne<Element_samp>)getProperty("element_samp")).getValue();
  }
}
