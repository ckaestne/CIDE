package tmp.generated_people;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Content_Any5 extends Content_Any {
  public Content_Any5(Element_gender element_gender, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<Element_gender>("element_gender", element_gender)
    }, firstToken, lastToken);
  }
  public Content_Any5(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public ASTNode deepCopy() {
    return new Content_Any5(cloneProperties(),firstToken,lastToken);
  }
  public Element_gender getElement_gender() {
    return ((PropertyOne<Element_gender>)getProperty("element_gender")).getValue();
  }
}
