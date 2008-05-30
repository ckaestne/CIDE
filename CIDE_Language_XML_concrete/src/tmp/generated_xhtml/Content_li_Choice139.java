package tmp.generated_xhtml;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Content_li_Choice139 extends Content_li_Choice1 {
  public Content_li_Choice139(Element_var element_var, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<Element_var>("element_var", element_var)
    }, firstToken, lastToken);
  }
  public Content_li_Choice139(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public ASTNode deepCopy() {
    return new Content_li_Choice139(cloneProperties(),firstToken,lastToken);
  }
  public Element_var getElement_var() {
    return ((PropertyOne<Element_var>)getProperty("element_var")).getValue();
  }
}
