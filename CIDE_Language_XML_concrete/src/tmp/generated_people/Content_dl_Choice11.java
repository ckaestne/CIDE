package tmp.generated_people;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Content_dl_Choice11 extends Content_dl_Choice1 {
  public Content_dl_Choice11(Element_dt element_dt, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<Element_dt>("element_dt", element_dt)
    }, firstToken, lastToken);
  }
  public Content_dl_Choice11(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public ASTNode deepCopy() {
    return new Content_dl_Choice11(cloneProperties(),firstToken,lastToken);
  }
  public Element_dt getElement_dt() {
    return ((PropertyOne<Element_dt>)getProperty("element_dt")).getValue();
  }
}
