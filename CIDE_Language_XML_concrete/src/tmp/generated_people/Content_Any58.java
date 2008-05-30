package tmp.generated_people;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Content_Any58 extends Content_Any {
  public Content_Any58(Element_colgroup element_colgroup, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<Element_colgroup>("element_colgroup", element_colgroup)
    }, firstToken, lastToken);
  }
  public Content_Any58(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public ASTNode deepCopy() {
    return new Content_Any58(cloneProperties(),firstToken,lastToken);
  }
  public Element_colgroup getElement_colgroup() {
    return ((PropertyOne<Element_colgroup>)getProperty("element_colgroup")).getValue();
  }
}
