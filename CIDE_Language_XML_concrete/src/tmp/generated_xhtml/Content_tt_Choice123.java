package tmp.generated_xhtml;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Content_tt_Choice123 extends Content_tt_Choice1 {
  public Content_tt_Choice123(Element_abbr element_abbr, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<Element_abbr>("element_abbr", element_abbr)
    }, firstToken, lastToken);
  }
  public Content_tt_Choice123(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public ASTNode deepCopy() {
    return new Content_tt_Choice123(cloneProperties(),firstToken,lastToken);
  }
  public Element_abbr getElement_abbr() {
    return ((PropertyOne<Element_abbr>)getProperty("element_abbr")).getValue();
  }
}
