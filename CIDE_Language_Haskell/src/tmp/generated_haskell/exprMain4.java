package tmp.generated_haskell;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class exprMain4 extends exprMain {
  public exprMain4(expr expr2, expr expr3, expr expr4, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<expr>("expr2", expr2),
      new PropertyOne<expr>("expr3", expr3),
      new PropertyOne<expr>("expr4", expr4)
    }, firstToken, lastToken);
  }
  public exprMain4(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new exprMain4(cloneProperties(),firstToken,lastToken);
  }
  public expr getExpr2() {
    return ((PropertyOne<expr>)getProperty("expr2")).getValue();
  }
  public expr getExpr3() {
    return ((PropertyOne<expr>)getProperty("expr3")).getValue();
  }
  public expr getExpr4() {
    return ((PropertyOne<expr>)getProperty("expr4")).getValue();
  }
}
