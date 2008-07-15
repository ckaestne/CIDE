package tmp.generated_html;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Element4 extends Element {
  public Element4(DeclTag declTag, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<DeclTag>("declTag", declTag)
    }, firstToken, lastToken);
  }
  public Element4(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new Element4(cloneProperties(),firstToken,lastToken);
  }
  public DeclTag getDeclTag() {
    return ((PropertyOne<DeclTag>)getProperty("declTag")).getValue();
  }
}
