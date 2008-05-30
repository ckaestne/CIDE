package tmp.generated_xhtml;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Content_div_Choice115 extends Content_div_Choice1 {
  public Content_div_Choice115(Element_blockquote element_blockquote, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<Element_blockquote>("element_blockquote", element_blockquote)
    }, firstToken, lastToken);
  }
  public Content_div_Choice115(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public ASTNode deepCopy() {
    return new Content_div_Choice115(cloneProperties(),firstToken,lastToken);
  }
  public Element_blockquote getElement_blockquote() {
    return ((PropertyOne<Element_blockquote>)getProperty("element_blockquote")).getValue();
  }
}
