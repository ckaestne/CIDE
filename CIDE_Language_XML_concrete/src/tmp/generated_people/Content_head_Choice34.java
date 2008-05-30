package tmp.generated_people;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Content_head_Choice34 extends Content_head_Choice3 {
  public Content_head_Choice34(Element_link element_link, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<Element_link>("element_link", element_link)
    }, firstToken, lastToken);
  }
  public Content_head_Choice34(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public ASTNode deepCopy() {
    return new Content_head_Choice34(cloneProperties(),firstToken,lastToken);
  }
  public Element_link getElement_link() {
    return ((PropertyOne<Element_link>)getProperty("element_link")).getValue();
  }
}
