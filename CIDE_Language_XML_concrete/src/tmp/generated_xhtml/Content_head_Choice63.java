package tmp.generated_xhtml;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Content_head_Choice63 extends Content_head_Choice6 {
  public Content_head_Choice63(Element_meta element_meta, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<Element_meta>("element_meta", element_meta)
    }, firstToken, lastToken);
  }
  public Content_head_Choice63(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public ASTNode deepCopy() {
    return new Content_head_Choice63(cloneProperties(),firstToken,lastToken);
  }
  public Element_meta getElement_meta() {
    return ((PropertyOne<Element_meta>)getProperty("element_meta")).getValue();
  }
}
