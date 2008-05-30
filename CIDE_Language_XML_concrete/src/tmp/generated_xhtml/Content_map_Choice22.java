package tmp.generated_xhtml;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Content_map_Choice22 extends Content_map_Choice2 {
  public Content_map_Choice22(Element_h1 element_h1, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<Element_h1>("element_h1", element_h1)
    }, firstToken, lastToken);
  }
  public Content_map_Choice22(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public ASTNode deepCopy() {
    return new Content_map_Choice22(cloneProperties(),firstToken,lastToken);
  }
  public Element_h1 getElement_h1() {
    return ((PropertyOne<Element_h1>)getProperty("element_h1")).getValue();
  }
}
