package tmp.generated_xhtml;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Content_dd_Choice118 extends Content_dd_Choice1 {
  public Content_dd_Choice118(Element_table element_table, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<Element_table>("element_table", element_table)
    }, firstToken, lastToken);
  }
  public Content_dd_Choice118(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public ASTNode deepCopy() {
    return new Content_dd_Choice118(cloneProperties(),firstToken,lastToken);
  }
  public Element_table getElement_table() {
    return ((PropertyOne<Element_table>)getProperty("element_table")).getValue();
  }
}
