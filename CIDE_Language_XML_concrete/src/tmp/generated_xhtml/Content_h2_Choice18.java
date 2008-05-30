package tmp.generated_xhtml;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Content_h2_Choice18 extends Content_h2_Choice1 {
  public Content_h2_Choice18(Element_img element_img, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<Element_img>("element_img", element_img)
    }, firstToken, lastToken);
  }
  public Content_h2_Choice18(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public ASTNode deepCopy() {
    return new Content_h2_Choice18(cloneProperties(),firstToken,lastToken);
  }
  public Element_img getElement_img() {
    return ((PropertyOne<Element_img>)getProperty("element_img")).getValue();
  }
}
