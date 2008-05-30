package tmp.generated_haskell;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class constructorOperatorMain2 extends constructorOperatorMain {
  public constructorOperatorMain2(otherOperators otherOperators, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<otherOperators>("otherOperators", otherOperators)
    }, firstToken, lastToken);
  }
  public constructorOperatorMain2(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public ASTNode deepCopy() {
    return new constructorOperatorMain2(cloneProperties(),firstToken,lastToken);
  }
  public otherOperators getOtherOperators() {
    return ((PropertyOne<otherOperators>)getProperty("otherOperators")).getValue();
  }
}
