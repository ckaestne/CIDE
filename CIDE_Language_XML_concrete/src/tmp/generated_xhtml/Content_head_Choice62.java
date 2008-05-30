package tmp.generated_xhtml;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Content_head_Choice62 extends Content_head_Choice6 {
  public Content_head_Choice62(Element_style element_style, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<Element_style>("element_style", element_style)
    }, firstToken, lastToken);
  }
  public Content_head_Choice62(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public ASTNode deepCopy() {
    return new Content_head_Choice62(cloneProperties(),firstToken,lastToken);
  }
  public Element_style getElement_style() {
    return ((PropertyOne<Element_style>)getProperty("element_style")).getValue();
  }
}
