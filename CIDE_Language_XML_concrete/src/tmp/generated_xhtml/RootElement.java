package tmp.generated_xhtml;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class RootElement extends GenASTNode {
  public RootElement(Element_html element_html, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<Element_html>("element_html", element_html)
    }, firstToken, lastToken);
  }
  public RootElement(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public ASTNode deepCopy() {
    return new RootElement(cloneProperties(),firstToken,lastToken);
  }
  public Element_html getElement_html() {
    return ((PropertyOne<Element_html>)getProperty("element_html")).getValue();
  }
}
