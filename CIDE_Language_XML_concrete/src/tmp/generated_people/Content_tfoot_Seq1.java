package tmp.generated_people;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Content_tfoot_Seq1 extends GenASTNode {
  public Content_tfoot_Seq1(Element_tr element_tr, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<Element_tr>("element_tr", element_tr)
    }, firstToken, lastToken);
  }
  public Content_tfoot_Seq1(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public ASTNode deepCopy() {
    return new Content_tfoot_Seq1(cloneProperties(),firstToken,lastToken);
  }
  public Element_tr getElement_tr() {
    return ((PropertyOne<Element_tr>)getProperty("element_tr")).getValue();
  }
}
