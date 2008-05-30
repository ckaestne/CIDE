package tmp.generated_people;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Content_blockquote_Choice117 extends Content_blockquote_Choice1 {
  public Content_blockquote_Choice117(Element_table element_table, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<Element_table>("element_table", element_table)
    }, firstToken, lastToken);
  }
  public Content_blockquote_Choice117(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public ASTNode deepCopy() {
    return new Content_blockquote_Choice117(cloneProperties(),firstToken,lastToken);
  }
  public Element_table getElement_table() {
    return ((PropertyOne<Element_table>)getProperty("element_table")).getValue();
  }
}
