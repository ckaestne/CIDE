package tmp.generated_people;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class RootElement extends GenASTNode {
  public RootElement(Element_people_list element_people_list, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<Element_people_list>("element_people_list", element_people_list)
    }, firstToken, lastToken);
  }
  public RootElement(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public ASTNode deepCopy() {
    return new RootElement(cloneProperties(),firstToken,lastToken);
  }
  public Element_people_list getElement_people_list() {
    return ((PropertyOne<Element_people_list>)getProperty("element_people_list")).getValue();
  }
}
