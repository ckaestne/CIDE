package tmp.generated_people;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Content_map_Choice25 extends Content_map_Choice2 {
  public Content_map_Choice25(Element_h4 element_h4, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<Element_h4>("element_h4", element_h4)
    }, firstToken, lastToken);
  }
  public Content_map_Choice25(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public ASTNode deepCopy() {
    return new Content_map_Choice25(cloneProperties(),firstToken,lastToken);
  }
  public Element_h4 getElement_h4() {
    return ((PropertyOne<Element_h4>)getProperty("element_h4")).getValue();
  }
}
