package tmp.generated_xhtml;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Content_fieldset_Choice121 extends Content_fieldset_Choice1 {
  public Content_fieldset_Choice121(Element_a element_a, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<Element_a>("element_a", element_a)
    }, firstToken, lastToken);
  }
  public Content_fieldset_Choice121(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public ASTNode deepCopy() {
    return new Content_fieldset_Choice121(cloneProperties(),firstToken,lastToken);
  }
  public Element_a getElement_a() {
    return ((PropertyOne<Element_a>)getProperty("element_a")).getValue();
  }
}
