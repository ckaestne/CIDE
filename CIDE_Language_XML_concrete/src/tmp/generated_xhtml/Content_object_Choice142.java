package tmp.generated_xhtml;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Content_object_Choice142 extends Content_object_Choice1 {
  public Content_object_Choice142(Element_abbr element_abbr, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<Element_abbr>("element_abbr", element_abbr)
    }, firstToken, lastToken);
  }
  public Content_object_Choice142(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public ASTNode deepCopy() {
    return new Content_object_Choice142(cloneProperties(),firstToken,lastToken);
  }
  public Element_abbr getElement_abbr() {
    return ((PropertyOne<Element_abbr>)getProperty("element_abbr")).getValue();
  }
}
