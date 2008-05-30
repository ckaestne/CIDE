package tmp.generated_people;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Content_head_Seq4 extends GenASTNode {
  public Content_head_Seq4(Element_base element_base, ArrayList<Content_head_Choice5> content_head_Choice5, Content_head_Seq5 content_head_Seq5, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<Element_base>("element_base", element_base),
      new PropertyZeroOrMore<Content_head_Choice5>("content_head_Choice5", content_head_Choice5),
      new PropertyOne<Content_head_Seq5>("content_head_Seq5", content_head_Seq5)
    }, firstToken, lastToken);
  }
  public Content_head_Seq4(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public ASTNode deepCopy() {
    return new Content_head_Seq4(cloneProperties(),firstToken,lastToken);
  }
  public Element_base getElement_base() {
    return ((PropertyOne<Element_base>)getProperty("element_base")).getValue();
  }
  public ArrayList<Content_head_Choice5> getContent_head_Choice5() {
    return ((PropertyZeroOrMore<Content_head_Choice5>)getProperty("content_head_Choice5")).getValue();
  }
  public Content_head_Seq5 getContent_head_Seq5() {
    return ((PropertyOne<Content_head_Seq5>)getProperty("content_head_Seq5")).getValue();
  }
}
