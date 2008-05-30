package tmp.generated_xhtml;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Content_head_Choice32 extends Content_head_Choice3 {
  public Content_head_Choice32(Element_style element_style, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<Element_style>("element_style", element_style)
    }, firstToken, lastToken);
  }
  public Content_head_Choice32(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public ASTNode deepCopy() {
    return new Content_head_Choice32(cloneProperties(),firstToken,lastToken);
  }
  public Element_style getElement_style() {
    return ((PropertyOne<Element_style>)getProperty("element_style")).getValue();
  }
}
