package tmp.generated_people;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Content_map_Choice211 extends Content_map_Choice2 {
  public Content_map_Choice211(Element_dl element_dl, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<Element_dl>("element_dl", element_dl)
    }, firstToken, lastToken);
  }
  public Content_map_Choice211(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public ASTNode deepCopy() {
    return new Content_map_Choice211(cloneProperties(),firstToken,lastToken);
  }
  public Element_dl getElement_dl() {
    return ((PropertyOne<Element_dl>)getProperty("element_dl")).getValue();
  }
}
