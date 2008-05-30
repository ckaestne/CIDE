package tmp.generated_people;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Content_head_Seq5 extends GenASTNode {
  public Content_head_Seq5(Element_title element_title, ArrayList<Content_head_Choice6> content_head_Choice6, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<Element_title>("element_title", element_title),
      new PropertyZeroOrMore<Content_head_Choice6>("content_head_Choice6", content_head_Choice6)
    }, firstToken, lastToken);
  }
  public Content_head_Seq5(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public ASTNode deepCopy() {
    return new Content_head_Seq5(cloneProperties(),firstToken,lastToken);
  }
  public Element_title getElement_title() {
    return ((PropertyOne<Element_title>)getProperty("element_title")).getValue();
  }
  public ArrayList<Content_head_Choice6> getContent_head_Choice6() {
    return ((PropertyZeroOrMore<Content_head_Choice6>)getProperty("content_head_Choice6")).getValue();
  }
}
