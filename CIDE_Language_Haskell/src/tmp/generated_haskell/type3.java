package tmp.generated_haskell;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class type3 extends type {
  public type3(functiontypeList functiontypeList, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyZeroOrOne<functiontypeList>("functiontypeList", functiontypeList)
    }, firstToken, lastToken);
  }
  public type3(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new type3(cloneProperties(),firstToken,lastToken);
  }
  public functiontypeList getFunctiontypeList() {
    return ((PropertyZeroOrOne<functiontypeList>)getProperty("functiontypeList")).getValue();
  }
}
