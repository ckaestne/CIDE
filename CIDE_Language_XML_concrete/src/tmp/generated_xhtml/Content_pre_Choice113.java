package tmp.generated_xhtml;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Content_pre_Choice113 extends Content_pre_Choice1 {
  public Content_pre_Choice113(Element_samp element_samp, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<Element_samp>("element_samp", element_samp)
    }, firstToken, lastToken);
  }
  public Content_pre_Choice113(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public ASTNode deepCopy() {
    return new Content_pre_Choice113(cloneProperties(),firstToken,lastToken);
  }
  public Element_samp getElement_samp() {
    return ((PropertyOne<Element_samp>)getProperty("element_samp")).getValue();
  }
}
