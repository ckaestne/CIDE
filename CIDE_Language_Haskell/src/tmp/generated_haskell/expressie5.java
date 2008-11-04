package tmp.generated_haskell;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class expressie5 extends expressie {
  public expressie5(operator operator1, expr expr1, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<operator>("operator1", operator1),
      new PropertyOne<expr>("expr1", expr1)
    }, firstToken, lastToken);
  }
  public expressie5(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new expressie5(cloneProperties(),firstToken,lastToken);
  }
  public operator getOperator1() {
    return ((PropertyOne<operator>)getProperty("operator1")).getValue();
  }
  public expr getExpr1() {
    return ((PropertyOne<expr>)getProperty("expr1")).getValue();
  }
}
