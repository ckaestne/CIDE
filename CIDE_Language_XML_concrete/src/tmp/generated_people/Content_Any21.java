package tmp.generated_people;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Content_Any21 extends Content_Any {
  public Content_Any21(Element_ins element_ins, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<Element_ins>("element_ins", element_ins)
    }, firstToken, lastToken);
  }
  public Content_Any21(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public ASTNode deepCopy() {
    return new Content_Any21(cloneProperties(),firstToken,lastToken);
  }
  public Element_ins getElement_ins() {
    return ((PropertyOne<Element_ins>)getProperty("element_ins")).getValue();
  }
}
