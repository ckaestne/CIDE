package tmp.generated_xhtml;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Content_fieldset_Choice138 extends Content_fieldset_Choice1 {
  public Content_fieldset_Choice138(Element_samp element_samp, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<Element_samp>("element_samp", element_samp)
    }, firstToken, lastToken);
  }
  public Content_fieldset_Choice138(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public ASTNode deepCopy() {
    return new Content_fieldset_Choice138(cloneProperties(),firstToken,lastToken);
  }
  public Element_samp getElement_samp() {
    return ((PropertyOne<Element_samp>)getProperty("element_samp")).getValue();
  }
}
