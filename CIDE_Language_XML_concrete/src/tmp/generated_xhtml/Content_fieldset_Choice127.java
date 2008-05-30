package tmp.generated_xhtml;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Content_fieldset_Choice127 extends Content_fieldset_Choice1 {
  public Content_fieldset_Choice127(Element_img element_img, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<Element_img>("element_img", element_img)
    }, firstToken, lastToken);
  }
  public Content_fieldset_Choice127(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public ASTNode deepCopy() {
    return new Content_fieldset_Choice127(cloneProperties(),firstToken,lastToken);
  }
  public Element_img getElement_img() {
    return ((PropertyOne<Element_img>)getProperty("element_img")).getValue();
  }
}
