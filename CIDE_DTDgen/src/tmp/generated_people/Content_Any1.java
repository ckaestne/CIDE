package tmp.generated_people;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Content_Any1 extends Content_Any {
  public Content_Any1(Element_people_list element_people_list, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<Element_people_list>("element_people_list", element_people_list)
    }, firstToken, lastToken);
  }
  public Content_Any1(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public ASTNode deepCopy() {
    return new Content_Any1(cloneProperties(),firstToken,lastToken);
  }
  public Element_people_list getElement_people_list() {
    return ((PropertyOne<Element_people_list>)getProperty("element_people_list")).getValue();
  }
}
