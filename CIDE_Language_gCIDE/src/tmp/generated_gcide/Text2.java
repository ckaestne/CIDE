package tmp.generated_gcide;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Text2 extends Text {
  public Text2(Java java, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<Java>("java", java)
    }, firstToken, lastToken);
  }
  public Text2(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new Text2(cloneProperties(),firstToken,lastToken);
  }
  public Java getJava() {
    return ((PropertyOne<Java>)getProperty("java")).getValue();
  }
}
