package tmp.generated_xhtml;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Content_acronym_Choice12 extends Content_acronym_Choice1 {
  public Content_acronym_Choice12(Element_a element_a, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<Element_a>("element_a", element_a)
    }, firstToken, lastToken);
  }
  public Content_acronym_Choice12(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public ASTNode deepCopy() {
    return new Content_acronym_Choice12(cloneProperties(),firstToken,lastToken);
  }
  public Element_a getElement_a() {
    return ((PropertyOne<Element_a>)getProperty("element_a")).getValue();
  }
}
