package tmp.generated_people;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Content_blockquote_Choice15 extends Content_blockquote_Choice1 {
  public Content_blockquote_Choice15(Element_h4 element_h4, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<Element_h4>("element_h4", element_h4)
    }, firstToken, lastToken);
  }
  public Content_blockquote_Choice15(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public ASTNode deepCopy() {
    return new Content_blockquote_Choice15(cloneProperties(),firstToken,lastToken);
  }
  public Element_h4 getElement_h4() {
    return ((PropertyOne<Element_h4>)getProperty("element_h4")).getValue();
  }
}
