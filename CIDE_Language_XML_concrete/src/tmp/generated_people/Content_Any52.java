package tmp.generated_people;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Content_Any52 extends Content_Any {
  public Content_Any52(Element_th element_th, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<Element_th>("element_th", element_th)
    }, firstToken, lastToken);
  }
  public Content_Any52(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public ASTNode deepCopy() {
    return new Content_Any52(cloneProperties(),firstToken,lastToken);
  }
  public Element_th getElement_th() {
    return ((PropertyOne<Element_th>)getProperty("element_th")).getValue();
  }
}
