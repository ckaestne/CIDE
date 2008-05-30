package tmp.generated_xhtml;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Content_table_Choice22 extends Content_table_Choice2 {
  public Content_table_Choice22(ArrayList<Element_tr> element_tr, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOneOrMore<Element_tr>("element_tr", element_tr)
    }, firstToken, lastToken);
  }
  public Content_table_Choice22(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public ASTNode deepCopy() {
    return new Content_table_Choice22(cloneProperties(),firstToken,lastToken);
  }
  public ArrayList<Element_tr> getElement_tr() {
    return ((PropertyOneOrMore<Element_tr>)getProperty("element_tr")).getValue();
  }
}
