package tmp.generated_haskell;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class expressie4 extends expressie {
  public expressie4(operator operator, expr expr, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<operator>("operator", operator),
      new PropertyOne<expr>("expr", expr)
    }, firstToken, lastToken);
  }
  public expressie4(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public ASTNode deepCopy() {
    return new expressie4(cloneProperties(),firstToken,lastToken);
  }
  public operator getOperator() {
    return ((PropertyOne<operator>)getProperty("operator")).getValue();
  }
  public expr getExpr() {
    return ((PropertyOne<expr>)getProperty("expr")).getValue();
  }
}
