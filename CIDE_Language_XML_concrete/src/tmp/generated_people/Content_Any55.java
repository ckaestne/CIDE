package tmp.generated_people;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Content_Any55 extends Content_Any {
  public Content_Any55(Element_tfoot element_tfoot, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<Element_tfoot>("element_tfoot", element_tfoot)
    }, firstToken, lastToken);
  }
  public Content_Any55(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public ASTNode deepCopy() {
    return new Content_Any55(cloneProperties(),firstToken,lastToken);
  }
  public Element_tfoot getElement_tfoot() {
    return ((PropertyOne<Element_tfoot>)getProperty("element_tfoot")).getValue();
  }
}
