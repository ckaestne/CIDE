package tmp.generated_people;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Content_Any75 extends Content_Any {
  public Content_Any75(Element_abbr element_abbr, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<Element_abbr>("element_abbr", element_abbr)
    }, firstToken, lastToken);
  }
  public Content_Any75(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public ASTNode deepCopy() {
    return new Content_Any75(cloneProperties(),firstToken,lastToken);
  }
  public Element_abbr getElement_abbr() {
    return ((PropertyOne<Element_abbr>)getProperty("element_abbr")).getValue();
  }
}
