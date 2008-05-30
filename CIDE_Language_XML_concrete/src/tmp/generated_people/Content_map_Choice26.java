package tmp.generated_people;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Content_map_Choice26 extends Content_map_Choice2 {
  public Content_map_Choice26(Element_h5 element_h5, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<Element_h5>("element_h5", element_h5)
    }, firstToken, lastToken);
  }
  public Content_map_Choice26(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public ASTNode deepCopy() {
    return new Content_map_Choice26(cloneProperties(),firstToken,lastToken);
  }
  public Element_h5 getElement_h5() {
    return ((PropertyOne<Element_h5>)getProperty("element_h5")).getValue();
  }
}
