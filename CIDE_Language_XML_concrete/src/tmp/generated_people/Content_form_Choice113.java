package tmp.generated_people;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Content_form_Choice113 extends Content_form_Choice1 {
  public Content_form_Choice113(Element_hr element_hr, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<Element_hr>("element_hr", element_hr)
    }, firstToken, lastToken);
  }
  public Content_form_Choice113(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public ASTNode deepCopy() {
    return new Content_form_Choice113(cloneProperties(),firstToken,lastToken);
  }
  public Element_hr getElement_hr() {
    return ((PropertyOne<Element_hr>)getProperty("element_hr")).getValue();
  }
}
