package tmp.generated_people;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Content_noscript_Choice119 extends Content_noscript_Choice1 {
  public Content_noscript_Choice119(Element_noscript element_noscript, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<Element_noscript>("element_noscript", element_noscript)
    }, firstToken, lastToken);
  }
  public Content_noscript_Choice119(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public ASTNode deepCopy() {
    return new Content_noscript_Choice119(cloneProperties(),firstToken,lastToken);
  }
  public Element_noscript getElement_noscript() {
    return ((PropertyOne<Element_noscript>)getProperty("element_noscript")).getValue();
  }
}
