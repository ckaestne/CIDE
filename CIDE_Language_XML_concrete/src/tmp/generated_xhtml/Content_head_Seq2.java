package tmp.generated_xhtml;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Content_head_Seq2 extends GenASTNode {
  public Content_head_Seq2(Element_title element_title, ArrayList<Content_head_Choice3> content_head_Choice3, Content_head_Seq3 content_head_Seq3, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<Element_title>("element_title", element_title),
      new PropertyZeroOrMore<Content_head_Choice3>("content_head_Choice3", content_head_Choice3),
      new PropertyZeroOrOne<Content_head_Seq3>("content_head_Seq3", content_head_Seq3)
    }, firstToken, lastToken);
  }
  public Content_head_Seq2(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public ASTNode deepCopy() {
    return new Content_head_Seq2(cloneProperties(),firstToken,lastToken);
  }
  public Element_title getElement_title() {
    return ((PropertyOne<Element_title>)getProperty("element_title")).getValue();
  }
  public ArrayList<Content_head_Choice3> getContent_head_Choice3() {
    return ((PropertyZeroOrMore<Content_head_Choice3>)getProperty("content_head_Choice3")).getValue();
  }
  public Content_head_Seq3 getContent_head_Seq3() {
    return ((PropertyZeroOrOne<Content_head_Seq3>)getProperty("content_head_Seq3")).getValue();
  }
}
