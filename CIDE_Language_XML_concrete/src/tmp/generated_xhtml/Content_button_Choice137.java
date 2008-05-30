package tmp.generated_xhtml;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Content_button_Choice137 extends Content_button_Choice1 {
  public Content_button_Choice137(Element_cite element_cite, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<Element_cite>("element_cite", element_cite)
    }, firstToken, lastToken);
  }
  public Content_button_Choice137(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public ASTNode deepCopy() {
    return new Content_button_Choice137(cloneProperties(),firstToken,lastToken);
  }
  public Element_cite getElement_cite() {
    return ((PropertyOne<Element_cite>)getProperty("element_cite")).getValue();
  }
}
