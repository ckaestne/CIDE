package tmp.generated_haskell;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class op3 extends op {
  public op3(otherOperators otherOperators, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<otherOperators>("otherOperators", otherOperators)
    }, firstToken, lastToken);
  }
  public op3(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new op3(cloneProperties(),firstToken,lastToken);
  }
  public otherOperators getOtherOperators() {
    return ((PropertyOne<otherOperators>)getProperty("otherOperators")).getValue();
  }
}
