package tmp.generated_people;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Content_ol_Seq1 extends GenASTNode {
  public Content_ol_Seq1(Element_li element_li, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<Element_li>("element_li", element_li)
    }, firstToken, lastToken);
  }
  public Content_ol_Seq1(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public ASTNode deepCopy() {
    return new Content_ol_Seq1(cloneProperties(),firstToken,lastToken);
  }
  public Element_li getElement_li() {
    return ((PropertyOne<Element_li>)getProperty("element_li")).getValue();
  }
}
