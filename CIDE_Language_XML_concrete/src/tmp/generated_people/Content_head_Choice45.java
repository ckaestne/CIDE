package tmp.generated_people;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Content_head_Choice45 extends Content_head_Choice4 {
  public Content_head_Choice45(Element_object element_object, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<Element_object>("element_object", element_object)
    }, firstToken, lastToken);
  }
  public Content_head_Choice45(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public ASTNode deepCopy() {
    return new Content_head_Choice45(cloneProperties(),firstToken,lastToken);
  }
  public Element_object getElement_object() {
    return ((PropertyOne<Element_object>)getProperty("element_object")).getValue();
  }
}
