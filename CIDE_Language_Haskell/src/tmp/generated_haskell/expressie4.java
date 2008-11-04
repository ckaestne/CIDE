package tmp.generated_haskell;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class expressie4 extends expressie {
  public expressie4(expr expr, operator operator, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<expr>("expr", expr),
      new PropertyOne<operator>("operator", operator)
    }, firstToken, lastToken);
  }
  public expressie4(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new expressie4(cloneProperties(),firstToken,lastToken);
  }
  public expr getExpr() {
    return ((PropertyOne<expr>)getProperty("expr")).getValue();
  }
  public operator getOperator() {
    return ((PropertyOne<operator>)getProperty("operator")).getValue();
  }
}
