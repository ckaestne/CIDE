package tmp.generated_xhtml;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Content_object_Choice116 extends Content_object_Choice1 {
  public Content_object_Choice116(Element_blockquote element_blockquote, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<Element_blockquote>("element_blockquote", element_blockquote)
    }, firstToken, lastToken);
  }
  public Content_object_Choice116(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public ASTNode deepCopy() {
    return new Content_object_Choice116(cloneProperties(),firstToken,lastToken);
  }
  public Element_blockquote getElement_blockquote() {
    return ((PropertyOne<Element_blockquote>)getProperty("element_blockquote")).getValue();
  }
}
