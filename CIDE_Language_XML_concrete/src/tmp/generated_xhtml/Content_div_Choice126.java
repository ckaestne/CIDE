package tmp.generated_xhtml;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Content_div_Choice126 extends Content_div_Choice1 {
  public Content_div_Choice126(Element_img element_img, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<Element_img>("element_img", element_img)
    }, firstToken, lastToken);
  }
  public Content_div_Choice126(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public ASTNode deepCopy() {
    return new Content_div_Choice126(cloneProperties(),firstToken,lastToken);
  }
  public Element_img getElement_img() {
    return ((PropertyOne<Element_img>)getProperty("element_img")).getValue();
  }
}
