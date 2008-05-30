package tmp.generated_xhtml;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Content_tr_Choice11 extends Content_tr_Choice1 {
  public Content_tr_Choice11(Element_th element_th, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<Element_th>("element_th", element_th)
    }, firstToken, lastToken);
  }
  public Content_tr_Choice11(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public ASTNode deepCopy() {
    return new Content_tr_Choice11(cloneProperties(),firstToken,lastToken);
  }
  public Element_th getElement_th() {
    return ((PropertyOne<Element_th>)getProperty("element_th")).getValue();
  }
}
