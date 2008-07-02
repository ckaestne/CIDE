package tmp.generated_haskell;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class exprListSpecial2 extends exprListSpecial {
  public exprListSpecial2(expr expr1, expr expr2, expr expr3, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<expr>("expr1", expr1),
      new PropertyZeroOrOne<expr>("expr2", expr2),
      new PropertyZeroOrOne<expr>("expr3", expr3)
    }, firstToken, lastToken);
  }
  public exprListSpecial2(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new exprListSpecial2(cloneProperties(),firstToken,lastToken);
  }
  public expr getExpr1() {
    return ((PropertyOne<expr>)getProperty("expr1")).getValue();
  }
  public expr getExpr2() {
    return ((PropertyZeroOrOne<expr>)getProperty("expr2")).getValue();
  }
  public expr getExpr3() {
    return ((PropertyZeroOrOne<expr>)getProperty("expr3")).getValue();
  }
}
