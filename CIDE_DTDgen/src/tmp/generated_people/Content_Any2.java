package tmp.generated_people;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Content_Any2 extends Content_Any {
  public Content_Any2(Element_person element_person, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<Element_person>("element_person", element_person)
    }, firstToken, lastToken);
  }
  public Content_Any2(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public ASTNode deepCopy() {
    return new Content_Any2(cloneProperties(),firstToken,lastToken);
  }
  public Element_person getElement_person() {
    return ((PropertyOne<Element_person>)getProperty("element_person")).getValue();
  }
}
