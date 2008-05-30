package tmp.generated_xhtml;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Content_table_Choice21 extends Content_table_Choice2 {
  public Content_table_Choice21(ArrayList<Element_tbody> element_tbody, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOneOrMore<Element_tbody>("element_tbody", element_tbody)
    }, firstToken, lastToken);
  }
  public Content_table_Choice21(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public ASTNode deepCopy() {
    return new Content_table_Choice21(cloneProperties(),firstToken,lastToken);
  }
  public ArrayList<Element_tbody> getElement_tbody() {
    return ((PropertyOneOrMore<Element_tbody>)getProperty("element_tbody")).getValue();
  }
}
