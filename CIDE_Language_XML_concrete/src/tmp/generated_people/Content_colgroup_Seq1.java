package tmp.generated_people;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Content_colgroup_Seq1 extends GenASTNode {
  public Content_colgroup_Seq1(Element_col element_col, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<Element_col>("element_col", element_col)
    }, firstToken, lastToken);
  }
  public Content_colgroup_Seq1(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public ASTNode deepCopy() {
    return new Content_colgroup_Seq1(cloneProperties(),firstToken,lastToken);
  }
  public Element_col getElement_col() {
    return ((PropertyOne<Element_col>)getProperty("element_col")).getValue();
  }
}
