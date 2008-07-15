package tmp.generated_html;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class ElementSequence extends GenASTNode {
  public ElementSequence(ArrayList<Element> element, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyZeroOrMore<Element>("element", element)
    }, firstToken, lastToken);
  }
  public ElementSequence(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new ElementSequence(cloneProperties(),firstToken,lastToken);
  }
  public ArrayList<Element> getElement() {
    return ((PropertyZeroOrMore<Element>)getProperty("element")).getValue();
  }
}
