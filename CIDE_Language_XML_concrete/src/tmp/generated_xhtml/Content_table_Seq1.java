package tmp.generated_xhtml;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Content_table_Seq1 extends GenASTNode {
  public Content_table_Seq1(Element_caption element_caption, Content_table_Choice1 content_table_Choice1, Element_thead element_thead, Element_tfoot element_tfoot, Content_table_Choice2 content_table_Choice2, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyZeroOrOne<Element_caption>("element_caption", element_caption),
      new PropertyOne<Content_table_Choice1>("content_table_Choice1", content_table_Choice1),
      new PropertyZeroOrOne<Element_thead>("element_thead", element_thead),
      new PropertyZeroOrOne<Element_tfoot>("element_tfoot", element_tfoot),
      new PropertyOne<Content_table_Choice2>("content_table_Choice2", content_table_Choice2)
    }, firstToken, lastToken);
  }
  public Content_table_Seq1(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public ASTNode deepCopy() {
    return new Content_table_Seq1(cloneProperties(),firstToken,lastToken);
  }
  public Element_caption getElement_caption() {
    return ((PropertyZeroOrOne<Element_caption>)getProperty("element_caption")).getValue();
  }
  public Content_table_Choice1 getContent_table_Choice1() {
    return ((PropertyOne<Content_table_Choice1>)getProperty("content_table_Choice1")).getValue();
  }
  public Element_thead getElement_thead() {
    return ((PropertyZeroOrOne<Element_thead>)getProperty("element_thead")).getValue();
  }
  public Element_tfoot getElement_tfoot() {
    return ((PropertyZeroOrOne<Element_tfoot>)getProperty("element_tfoot")).getValue();
  }
  public Content_table_Choice2 getContent_table_Choice2() {
    return ((PropertyOne<Content_table_Choice2>)getProperty("content_table_Choice2")).getValue();
  }
}
