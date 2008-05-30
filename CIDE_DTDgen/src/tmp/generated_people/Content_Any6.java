package tmp.generated_people;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Content_Any6 extends Content_Any {
  public Content_Any6(Element_socialsecuritynumber element_socialsecuritynumber, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<Element_socialsecuritynumber>("element_socialsecuritynumber", element_socialsecuritynumber)
    }, firstToken, lastToken);
  }
  public Content_Any6(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public ASTNode deepCopy() {
    return new Content_Any6(cloneProperties(),firstToken,lastToken);
  }
  public Element_socialsecuritynumber getElement_socialsecuritynumber() {
    return ((PropertyOne<Element_socialsecuritynumber>)getProperty("element_socialsecuritynumber")).getValue();
  }
}
