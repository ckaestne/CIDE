package tmp.generated_phaskell;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class funlhsL2 extends funlhsL {
  public funlhsL2(var var, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<var>("var", var)
    }, firstToken, lastToken);
  }
  public funlhsL2(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new funlhsL2(cloneProperties(),firstToken,lastToken);
  }
  public var getVar() {
    return ((PropertyOne<var>)getProperty("var")).getValue();
  }
}
