package tmp.generated_xhtml;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Content_ins_Choice134 extends Content_ins_Choice1 {
  public Content_ins_Choice134(Element_dfn element_dfn, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<Element_dfn>("element_dfn", element_dfn)
    }, firstToken, lastToken);
  }
  public Content_ins_Choice134(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public ASTNode deepCopy() {
    return new Content_ins_Choice134(cloneProperties(),firstToken,lastToken);
  }
  public Element_dfn getElement_dfn() {
    return ((PropertyOne<Element_dfn>)getProperty("element_dfn")).getValue();
  }
}
