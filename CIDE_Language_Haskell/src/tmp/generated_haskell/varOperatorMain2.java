package tmp.generated_haskell;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class varOperatorMain2 extends varOperatorMain {
  public varOperatorMain2(otherOperators otherOperators, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<otherOperators>("otherOperators", otherOperators)
    }, firstToken, lastToken);
  }
  public varOperatorMain2(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public ASTNode deepCopy() {
    return new varOperatorMain2(cloneProperties(),firstToken,lastToken);
  }
  public otherOperators getOtherOperators() {
    return ((PropertyOne<otherOperators>)getProperty("otherOperators")).getValue();
  }
}
