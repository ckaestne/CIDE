package tmp.generated_people;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Content_head_Seq3 extends GenASTNode {
  public Content_head_Seq3(Element_base element_base, ArrayList<Content_head_Choice4> content_head_Choice4, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<Element_base>("element_base", element_base),
      new PropertyZeroOrMore<Content_head_Choice4>("content_head_Choice4", content_head_Choice4)
    }, firstToken, lastToken);
  }
  public Content_head_Seq3(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public ASTNode deepCopy() {
    return new Content_head_Seq3(cloneProperties(),firstToken,lastToken);
  }
  public Element_base getElement_base() {
    return ((PropertyOne<Element_base>)getProperty("element_base")).getValue();
  }
  public ArrayList<Content_head_Choice4> getContent_head_Choice4() {
    return ((PropertyZeroOrMore<Content_head_Choice4>)getProperty("content_head_Choice4")).getValue();
  }
}
