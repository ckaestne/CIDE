package tmp.generated_xhtml;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Content_html_Seq1 extends GenASTNode {
  public Content_html_Seq1(Element_head element_head, Element_body element_body, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<Element_head>("element_head", element_head),
      new PropertyOne<Element_body>("element_body", element_body)
    }, firstToken, lastToken);
  }
  public Content_html_Seq1(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public ASTNode deepCopy() {
    return new Content_html_Seq1(cloneProperties(),firstToken,lastToken);
  }
  public Element_head getElement_head() {
    return ((PropertyOne<Element_head>)getProperty("element_head")).getValue();
  }
  public Element_body getElement_body() {
    return ((PropertyOne<Element_body>)getProperty("element_body")).getValue();
  }
}
