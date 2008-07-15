package tmp.generated_html;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Element2 extends Element {
  public Element2(EndTag endTag, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<EndTag>("endTag", endTag)
    }, firstToken, lastToken);
  }
  public Element2(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new Element2(cloneProperties(),firstToken,lastToken);
  }
  public EndTag getEndTag() {
    return ((PropertyOne<EndTag>)getProperty("endTag")).getValue();
  }
}
