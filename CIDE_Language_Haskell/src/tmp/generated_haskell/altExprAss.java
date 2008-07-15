package tmp.generated_haskell;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class altExprAss extends GenASTNode {
  public altExprAss(expr expr, expr expr1, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<expr>("expr", expr),
      new PropertyOne<expr>("expr1", expr1)
    }, firstToken, lastToken);
  }
  public altExprAss(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new altExprAss(cloneProperties(),firstToken,lastToken);
  }
  public expr getExpr() {
    return ((PropertyOne<expr>)getProperty("expr")).getValue();
  }
  public expr getExpr1() {
    return ((PropertyOne<expr>)getProperty("expr1")).getValue();
  }
}
