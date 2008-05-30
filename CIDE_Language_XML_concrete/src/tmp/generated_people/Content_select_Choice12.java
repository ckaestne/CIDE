package tmp.generated_people;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Content_select_Choice12 extends Content_select_Choice1 {
  public Content_select_Choice12(Element_option element_option, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<Element_option>("element_option", element_option)
    }, firstToken, lastToken);
  }
  public Content_select_Choice12(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public ASTNode deepCopy() {
    return new Content_select_Choice12(cloneProperties(),firstToken,lastToken);
  }
  public Element_option getElement_option() {
    return ((PropertyOne<Element_option>)getProperty("element_option")).getValue();
  }
}
