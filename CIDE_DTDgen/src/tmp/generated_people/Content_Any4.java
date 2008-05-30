package tmp.generated_people;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Content_Any4 extends Content_Any {
  public Content_Any4(Element_birthdate element_birthdate, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<Element_birthdate>("element_birthdate", element_birthdate)
    }, firstToken, lastToken);
  }
  public Content_Any4(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public ASTNode deepCopy() {
    return new Content_Any4(cloneProperties(),firstToken,lastToken);
  }
  public Element_birthdate getElement_birthdate() {
    return ((PropertyOne<Element_birthdate>)getProperty("element_birthdate")).getValue();
  }
}
