package tmp.generated_xhtml;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Content_th_Choice127 extends Content_th_Choice1 {
  public Content_th_Choice127(Element_tt element_tt, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<Element_tt>("element_tt", element_tt)
    }, firstToken, lastToken);
  }
  public Content_th_Choice127(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public ASTNode deepCopy() {
    return new Content_th_Choice127(cloneProperties(),firstToken,lastToken);
  }
  public Element_tt getElement_tt() {
    return ((PropertyOne<Element_tt>)getProperty("element_tt")).getValue();
  }
}
