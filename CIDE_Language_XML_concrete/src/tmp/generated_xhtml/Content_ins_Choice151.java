package tmp.generated_xhtml;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Content_ins_Choice151 extends Content_ins_Choice1 {
  public Content_ins_Choice151(Element_ins element_ins, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<Element_ins>("element_ins", element_ins)
    }, firstToken, lastToken);
  }
  public Content_ins_Choice151(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public ASTNode deepCopy() {
    return new Content_ins_Choice151(cloneProperties(),firstToken,lastToken);
  }
  public Element_ins getElement_ins() {
    return ((PropertyOne<Element_ins>)getProperty("element_ins")).getValue();
  }
}
