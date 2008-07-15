package tmp.generated_html;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Element1 extends Element {
  public Element1(Tag tag, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<Tag>("tag", tag)
    }, firstToken, lastToken);
  }
  public Element1(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new Element1(cloneProperties(),firstToken,lastToken);
  }
  public Tag getTag() {
    return ((PropertyOne<Tag>)getProperty("tag")).getValue();
  }
}
