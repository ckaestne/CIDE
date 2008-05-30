package tmp.generated_xhtml;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Content_div_Choice13 extends Content_div_Choice1 {
  public Content_div_Choice13(Element_h1 element_h1, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<Element_h1>("element_h1", element_h1)
    }, firstToken, lastToken);
  }
  public Content_div_Choice13(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public ASTNode deepCopy() {
    return new Content_div_Choice13(cloneProperties(),firstToken,lastToken);
  }
  public Element_h1 getElement_h1() {
    return ((PropertyOne<Element_h1>)getProperty("element_h1")).getValue();
  }
}
