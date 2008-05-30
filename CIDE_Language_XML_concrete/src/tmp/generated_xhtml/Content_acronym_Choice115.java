package tmp.generated_xhtml;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Content_acronym_Choice115 extends Content_acronym_Choice1 {
  public Content_acronym_Choice115(Element_strong element_strong, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<Element_strong>("element_strong", element_strong)
    }, firstToken, lastToken);
  }
  public Content_acronym_Choice115(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public ASTNode deepCopy() {
    return new Content_acronym_Choice115(cloneProperties(),firstToken,lastToken);
  }
  public Element_strong getElement_strong() {
    return ((PropertyOne<Element_strong>)getProperty("element_strong")).getValue();
  }
}
