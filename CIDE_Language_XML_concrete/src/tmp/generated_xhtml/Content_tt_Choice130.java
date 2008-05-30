package tmp.generated_xhtml;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Content_tt_Choice130 extends Content_tt_Choice1 {
  public Content_tt_Choice130(Element_label element_label, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<Element_label>("element_label", element_label)
    }, firstToken, lastToken);
  }
  public Content_tt_Choice130(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public ASTNode deepCopy() {
    return new Content_tt_Choice130(cloneProperties(),firstToken,lastToken);
  }
  public Element_label getElement_label() {
    return ((PropertyOne<Element_label>)getProperty("element_label")).getValue();
  }
}
