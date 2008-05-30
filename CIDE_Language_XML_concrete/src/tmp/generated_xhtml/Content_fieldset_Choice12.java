package tmp.generated_xhtml;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Content_fieldset_Choice12 extends Content_fieldset_Choice1 {
  public Content_fieldset_Choice12(Element_legend element_legend, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<Element_legend>("element_legend", element_legend)
    }, firstToken, lastToken);
  }
  public Content_fieldset_Choice12(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public ASTNode deepCopy() {
    return new Content_fieldset_Choice12(cloneProperties(),firstToken,lastToken);
  }
  public Element_legend getElement_legend() {
    return ((PropertyOne<Element_legend>)getProperty("element_legend")).getValue();
  }
}
