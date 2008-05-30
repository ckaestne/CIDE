package tmp.generated_xhtml;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Content_Any26 extends Content_Any {
  public Content_Any26(Element_col element_col, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<Element_col>("element_col", element_col)
    }, firstToken, lastToken);
  }
  public Content_Any26(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public ASTNode deepCopy() {
    return new Content_Any26(cloneProperties(),firstToken,lastToken);
  }
  public Element_col getElement_col() {
    return ((PropertyOne<Element_col>)getProperty("element_col")).getValue();
  }
}
