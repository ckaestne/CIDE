package tmp.generated_people;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Content_Any42 extends Content_Any {
  public Content_Any42(Element_caption element_caption, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<Element_caption>("element_caption", element_caption)
    }, firstToken, lastToken);
  }
  public Content_Any42(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public ASTNode deepCopy() {
    return new Content_Any42(cloneProperties(),firstToken,lastToken);
  }
  public Element_caption getElement_caption() {
    return ((PropertyOne<Element_caption>)getProperty("element_caption")).getValue();
  }
}
