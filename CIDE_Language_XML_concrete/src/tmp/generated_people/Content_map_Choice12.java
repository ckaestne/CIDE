package tmp.generated_people;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Content_map_Choice12 extends Content_map_Choice1 {
  public Content_map_Choice12(ArrayList<Element_area> element_area, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOneOrMore<Element_area>("element_area", element_area)
    }, firstToken, lastToken);
  }
  public Content_map_Choice12(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public ASTNode deepCopy() {
    return new Content_map_Choice12(cloneProperties(),firstToken,lastToken);
  }
  public ArrayList<Element_area> getElement_area() {
    return ((PropertyOneOrMore<Element_area>)getProperty("element_area")).getValue();
  }
}
