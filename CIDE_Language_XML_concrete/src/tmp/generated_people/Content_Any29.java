package tmp.generated_people;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Content_Any29 extends Content_Any {
  public Content_Any29(Element_meta element_meta, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<Element_meta>("element_meta", element_meta)
    }, firstToken, lastToken);
  }
  public Content_Any29(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public ASTNode deepCopy() {
    return new Content_Any29(cloneProperties(),firstToken,lastToken);
  }
  public Element_meta getElement_meta() {
    return ((PropertyOne<Element_meta>)getProperty("element_meta")).getValue();
  }
}
