package tmp.generated_xhtml;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Content_Any4 extends Content_Any {
  public Content_Any4(Element_em element_em, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<Element_em>("element_em", element_em)
    }, firstToken, lastToken);
  }
  public Content_Any4(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public ASTNode deepCopy() {
    return new Content_Any4(cloneProperties(),firstToken,lastToken);
  }
  public Element_em getElement_em() {
    return ((PropertyOne<Element_em>)getProperty("element_em")).getValue();
  }
}
