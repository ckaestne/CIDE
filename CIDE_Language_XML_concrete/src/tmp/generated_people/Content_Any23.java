package tmp.generated_people;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Content_Any23 extends Content_Any {
  public Content_Any23(Element_dd element_dd, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<Element_dd>("element_dd", element_dd)
    }, firstToken, lastToken);
  }
  public Content_Any23(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public ASTNode deepCopy() {
    return new Content_Any23(cloneProperties(),firstToken,lastToken);
  }
  public Element_dd getElement_dd() {
    return ((PropertyOne<Element_dd>)getProperty("element_dd")).getValue();
  }
}
