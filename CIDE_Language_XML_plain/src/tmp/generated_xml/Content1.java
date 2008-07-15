package tmp.generated_xml;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Content1 extends Content {
  public Content1(Element element, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<Element>("element", element)
    }, firstToken, lastToken);
  }
  public Content1(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new Content1(cloneProperties(),firstToken,lastToken);
  }
  public Element getElement() {
    return ((PropertyOne<Element>)getProperty("element")).getValue();
  }
}
