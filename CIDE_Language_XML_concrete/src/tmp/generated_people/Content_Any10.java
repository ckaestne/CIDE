package tmp.generated_people;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Content_Any10 extends Content_Any {
  public Content_Any10(Element_label element_label, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<Element_label>("element_label", element_label)
    }, firstToken, lastToken);
  }
  public Content_Any10(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public ASTNode deepCopy() {
    return new Content_Any10(cloneProperties(),firstToken,lastToken);
  }
  public Element_label getElement_label() {
    return ((PropertyOne<Element_label>)getProperty("element_label")).getValue();
  }
}
