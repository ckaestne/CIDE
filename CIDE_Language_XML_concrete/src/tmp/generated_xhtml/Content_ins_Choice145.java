package tmp.generated_xhtml;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Content_ins_Choice145 extends Content_ins_Choice1 {
  public Content_ins_Choice145(Element_input element_input, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<Element_input>("element_input", element_input)
    }, firstToken, lastToken);
  }
  public Content_ins_Choice145(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public ASTNode deepCopy() {
    return new Content_ins_Choice145(cloneProperties(),firstToken,lastToken);
  }
  public Element_input getElement_input() {
    return ((PropertyOne<Element_input>)getProperty("element_input")).getValue();
  }
}
