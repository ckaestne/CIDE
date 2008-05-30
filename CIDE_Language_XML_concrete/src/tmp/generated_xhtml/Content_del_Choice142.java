package tmp.generated_xhtml;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Content_del_Choice142 extends Content_del_Choice1 {
  public Content_del_Choice142(Element_acronym element_acronym, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<Element_acronym>("element_acronym", element_acronym)
    }, firstToken, lastToken);
  }
  public Content_del_Choice142(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public ASTNode deepCopy() {
    return new Content_del_Choice142(cloneProperties(),firstToken,lastToken);
  }
  public Element_acronym getElement_acronym() {
    return ((PropertyOne<Element_acronym>)getProperty("element_acronym")).getValue();
  }
}
