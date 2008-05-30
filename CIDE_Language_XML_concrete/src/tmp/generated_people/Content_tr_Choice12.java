package tmp.generated_people;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Content_tr_Choice12 extends Content_tr_Choice1 {
  public Content_tr_Choice12(Element_td element_td, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<Element_td>("element_td", element_td)
    }, firstToken, lastToken);
  }
  public Content_tr_Choice12(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public ASTNode deepCopy() {
    return new Content_tr_Choice12(cloneProperties(),firstToken,lastToken);
  }
  public Element_td getElement_td() {
    return ((PropertyOne<Element_td>)getProperty("element_td")).getValue();
  }
}
