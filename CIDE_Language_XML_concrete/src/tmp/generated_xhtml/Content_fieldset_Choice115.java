package tmp.generated_xhtml;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Content_fieldset_Choice115 extends Content_fieldset_Choice1 {
  public Content_fieldset_Choice115(Element_hr element_hr, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<Element_hr>("element_hr", element_hr)
    }, firstToken, lastToken);
  }
  public Content_fieldset_Choice115(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public ASTNode deepCopy() {
    return new Content_fieldset_Choice115(cloneProperties(),firstToken,lastToken);
  }
  public Element_hr getElement_hr() {
    return ((PropertyOne<Element_hr>)getProperty("element_hr")).getValue();
  }
}
