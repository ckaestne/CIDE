package tmp.generated_xhtml;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Content_fieldset_Choice133 extends Content_fieldset_Choice1 {
  public Content_fieldset_Choice133(Element_em element_em, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<Element_em>("element_em", element_em)
    }, firstToken, lastToken);
  }
  public Content_fieldset_Choice133(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public ASTNode deepCopy() {
    return new Content_fieldset_Choice133(cloneProperties(),firstToken,lastToken);
  }
  public Element_em getElement_em() {
    return ((PropertyOne<Element_em>)getProperty("element_em")).getValue();
  }
}
