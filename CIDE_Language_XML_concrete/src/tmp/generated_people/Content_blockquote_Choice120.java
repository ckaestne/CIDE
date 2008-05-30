package tmp.generated_people;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Content_blockquote_Choice120 extends Content_blockquote_Choice1 {
  public Content_blockquote_Choice120(Element_ins element_ins, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<Element_ins>("element_ins", element_ins)
    }, firstToken, lastToken);
  }
  public Content_blockquote_Choice120(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public ASTNode deepCopy() {
    return new Content_blockquote_Choice120(cloneProperties(),firstToken,lastToken);
  }
  public Element_ins getElement_ins() {
    return ((PropertyOne<Element_ins>)getProperty("element_ins")).getValue();
  }
}
