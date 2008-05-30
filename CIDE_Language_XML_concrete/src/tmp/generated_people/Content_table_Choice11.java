package tmp.generated_people;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Content_table_Choice11 extends Content_table_Choice1 {
  public Content_table_Choice11(ArrayList<Element_col> element_col, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyZeroOrMore<Element_col>("element_col", element_col)
    }, firstToken, lastToken);
  }
  public Content_table_Choice11(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public ASTNode deepCopy() {
    return new Content_table_Choice11(cloneProperties(),firstToken,lastToken);
  }
  public ArrayList<Element_col> getElement_col() {
    return ((PropertyZeroOrMore<Element_col>)getProperty("element_col")).getValue();
  }
}
