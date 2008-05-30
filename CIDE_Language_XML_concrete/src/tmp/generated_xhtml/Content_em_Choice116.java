package tmp.generated_xhtml;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Content_em_Choice116 extends Content_em_Choice1 {
  public Content_em_Choice116(Element_dfn element_dfn, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<Element_dfn>("element_dfn", element_dfn)
    }, firstToken, lastToken);
  }
  public Content_em_Choice116(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public ASTNode deepCopy() {
    return new Content_em_Choice116(cloneProperties(),firstToken,lastToken);
  }
  public Element_dfn getElement_dfn() {
    return ((PropertyOne<Element_dfn>)getProperty("element_dfn")).getValue();
  }
}
