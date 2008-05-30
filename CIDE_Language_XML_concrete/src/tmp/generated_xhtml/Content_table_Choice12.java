package tmp.generated_xhtml;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Content_table_Choice12 extends Content_table_Choice1 {
  public Content_table_Choice12(ArrayList<Element_colgroup> element_colgroup, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyZeroOrMore<Element_colgroup>("element_colgroup", element_colgroup)
    }, firstToken, lastToken);
  }
  public Content_table_Choice12(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public ASTNode deepCopy() {
    return new Content_table_Choice12(cloneProperties(),firstToken,lastToken);
  }
  public ArrayList<Element_colgroup> getElement_colgroup() {
    return ((PropertyZeroOrMore<Element_colgroup>)getProperty("element_colgroup")).getValue();
  }
}
