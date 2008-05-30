package tmp.generated_people;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Content_Any74 extends Content_Any {
  public Content_Any74(Element_legend element_legend, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<Element_legend>("element_legend", element_legend)
    }, firstToken, lastToken);
  }
  public Content_Any74(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public ASTNode deepCopy() {
    return new Content_Any74(cloneProperties(),firstToken,lastToken);
  }
  public Element_legend getElement_legend() {
    return ((PropertyOne<Element_legend>)getProperty("element_legend")).getValue();
  }
}
