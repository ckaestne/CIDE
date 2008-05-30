package tmp.generated_xhtml;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Content_pre_Choice110 extends Content_pre_Choice1 {
  public Content_pre_Choice110(Element_dfn element_dfn, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<Element_dfn>("element_dfn", element_dfn)
    }, firstToken, lastToken);
  }
  public Content_pre_Choice110(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public ASTNode deepCopy() {
    return new Content_pre_Choice110(cloneProperties(),firstToken,lastToken);
  }
  public Element_dfn getElement_dfn() {
    return ((PropertyOne<Element_dfn>)getProperty("element_dfn")).getValue();
  }
}
