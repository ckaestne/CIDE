package tmp.generated_xhtml;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Content_th_Choice146 extends Content_th_Choice1 {
  public Content_th_Choice146(Element_select element_select, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<Element_select>("element_select", element_select)
    }, firstToken, lastToken);
  }
  public Content_th_Choice146(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public ASTNode deepCopy() {
    return new Content_th_Choice146(cloneProperties(),firstToken,lastToken);
  }
  public Element_select getElement_select() {
    return ((PropertyOne<Element_select>)getProperty("element_select")).getValue();
  }
}
